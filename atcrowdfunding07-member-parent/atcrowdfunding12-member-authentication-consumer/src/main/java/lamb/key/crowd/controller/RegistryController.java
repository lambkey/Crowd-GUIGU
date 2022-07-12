package lamb.key.crowd.controller;

import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.api.RedisRemoteService;
import lamb.key.crowd.config.PropertiesSmsConfig;
import lamb.key.crowd.po.MemberPO;
import lamb.key.crowd.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import net.seehope.crowd.constant.CrowdConstant;
import net.seehope.crowd.util.CrowdUtil;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author JoinYang
 * @date 2022/6/2 19:17
 * 注册会员
 * 发送验证码
 */

@Controller
@Slf4j
public class RegistryController {

    // 装配发送短信配置类，具体参数在yml里面改
    @Autowired
    private PropertiesSmsConfig propertiesSmsConfig;

    // 远程调用feign提供的redis远程接口，装配
    @Autowired
    private RedisRemoteService redisRemoteService;

    // 远程调用feign提供的mysql远程接口，装配
    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    // 装配加密类
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     *@author JoinYang
     *@date: 2022/6/4
     * 1.接收前端电话号码，发送验证码
     */

    @RequestMapping("/registry/send/sms")
    @ResponseBody
    public JSONResult<String> RegistrySendSms(@RequestParam("phoneNumber") String phoneNumber) {

        // 调用调用第三方接口的工具类
        JSONResult<String> stringJSONResult = CrowdUtil.sendSmsCode(propertiesSmsConfig.getAppid(),
                propertiesSmsConfig.getAppKey(), propertiesSmsConfig.getTemplateId(),
                propertiesSmsConfig.getSignContent(), propertiesSmsConfig.getValidTime(), phoneNumber);

        // 判断如果结果为FAILURE则返回FAILURE给前端
        if (stringJSONResult.getResult() == JSONResult.FAILURE) {

            return JSONResult.FailureNeedMessage(stringJSONResult.getMessage());

        // 如果调用第三方接口返回结果不为FAILURE,则携带短信验证码返回到这里，下一步进行redis缓存
        } else {

            // 将手机号与CrowdConstant.ATTR_NAME_REDIS_SMS拼接存入redis，返回结果
            redisRemoteService.getTimeoutValueRedisRemote(CrowdConstant.ATTR_NAME_REDIS_SMS+phoneNumber, stringJSONResult.getMessage(), propertiesSmsConfig.getValidTime());

            return JSONResult.successWithoutData();

        }
    }


    /**
     *@author JoinYang
     *@date: 2022/6/4
     * 2.接收前端电话号码，发送验证码
     */
    // 2.用户前端提交了个人资料，要进行vo封装，检验保存用户个人数据
    @RequestMapping("/registry/save/info")
    public String RegistrySaveInfo(MemberVO memberVO,ModelMap modelMap){


        String phone = memberVO.getPhoneNum();

        JSONResult<String> valueRedisRemote = redisRemoteService.getValueRedisRemote(CrowdConstant.ATTR_NAME_REDIS_SMS+phone);

        // 判断redis服务中返回结果的是否为空
        if (valueRedisRemote.getMessage()==null){
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,"未发送验证码或验证码已过期 请重新发送！");
            return "registry-page";
        }


        // 判断用户输入的验证码，和用户在redis里面存放的验证码是否一致
        if (!Objects.equals(valueRedisRemote.getMessage(),memberVO.getCode())){
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,"验证码错误 请重新输入！");
            return "registry-page";
        }

        // 判断用户输入的账号密码是否为空
        if (memberVO.getLoginacct()==null||memberVO.getUsername()==null){
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,"账号或密码能为空 请重新输入！");
            return "registry-page";
        }

        try {

            MemberPO memberPO =new MemberPO();

            // 给会员密码加密
            String passwordVO = passwordEncoder.encode(memberVO.getUserpswd());
            memberVO.setUserpswd(passwordVO);

            // 把VO对象转换为PO对象
            BeanUtils.copyProperties(memberVO,memberPO);

            JSONResult<String> stringJSONResult = mySQLRemoteService.saveMemberPOInfoRemote(memberPO);

            // 保存数据成功
            if (stringJSONResult.getResult().equals(JSONResult.SUCCESS)){

                // 会员资料验证成功，删除redis中的验证码
                JSONResult<String> stringJSONResult1 = redisRemoteService.deleteRedisKey(CrowdConstant.ATTR_NAME_REDIS_SMS + phone);
                if (!stringJSONResult1.getResult().equals(JSONResult.FAILURE)){
                    log.info("--- ---用户验证码验证成功,验证码为{}",valueRedisRemote.getMessage());
                }

                // 保存成功就跳转到登录页面
                return "redirect:/page/to/login.html";

            }
            // 用户已存在
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,stringJSONResult.getMessage());
            return "registry-page";
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,"系统注册异常请联系管理员");
        return "registry-page";
    }


}
