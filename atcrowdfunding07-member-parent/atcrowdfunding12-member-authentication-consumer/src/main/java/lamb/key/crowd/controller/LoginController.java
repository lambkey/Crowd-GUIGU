package lamb.key.crowd.controller;

import com.alibaba.fastjson.JSONObject;
import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.po.MemberPO;
import lamb.key.crowd.vo.MemberLoginVO;
import lamb.key.crowd.vo.MemberVO;
import net.seehope.crowd.constant.CrowdConstant;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author JoinYang
 * @date 2022/6/5 14:28
 * 登录验证
 * 退出登录
 */
@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    /**
     * @author JoinYang
     * @date: 2022/6/5
     * 会员登录验证处理
     */
    @RequestMapping("/member/do/login")
    public String doLogin(MemberVO memberVO, ModelMap modelMap, HttpSession httpSession) {

        // 获取会员登录传进来的账号，根据会员账号查询是否存在
        String loginAcct = memberVO.getLoginacct();

        JSONResult<MemberPO> memberPOByLoginAcctRemote = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginAcct);


        if (!memberPOByLoginAcctRemote.getResult().equals(JSONResult.SUCCESS)) {
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR, CrowdConstant.MASSAGE_LOGIN_FAILED);
            return "login";
        }


        // 对会员输入的密码与数据库里面的密码进行对比
        String s = JSONObject.toJSONString(memberPOByLoginAcctRemote.getData());
        MemberPO memberPO = JSONObject.parseObject(s, MemberPO.class);
        String pswdDB = memberPO.getUserpswd();
        boolean matches = passwordEncoder.matches(memberVO.getUserpswd(), pswdDB);

        if (!matches) {
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR, CrowdConstant.MASSAGE_LOGIN_FAILED);
            return "login";
        }

        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(),memberPO.getLoginacct(), memberPO.getUsername());

        httpSession.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN_SESSION,memberLoginVO);

        // 原来是不同的服务
        // 保持与zuul的cookie一致
        // 在此重定向到的服务是zuul的服务，所以在不要忘记显示在前端的memberLoginVO对象
        // 因此我们的zuul服务需要依赖entity模块
        return "redirect:http://localhost:9000/auth/success/to/member/main";
    }
    /**
     *@author JoinYang
     *@date: 2022/6/5
     * 会员退出登录
     */
    @RequestMapping("member/do/logout")
    public String dpLogout(HttpSession httpSession){

        httpSession.invalidate();

        return "redirect:http:localhost:9000/page/to/login.html";
    }
}
