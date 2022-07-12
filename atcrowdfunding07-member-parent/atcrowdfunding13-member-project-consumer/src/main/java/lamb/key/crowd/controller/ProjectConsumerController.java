package lamb.key.crowd.controller;

import com.alibaba.fastjson.JSON;
import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.config.COSPropertiesConfig;
import lamb.key.crowd.utils.UploadMsg;
import lamb.key.crowd.utils.UploadUtils;
import lamb.key.crowd.vo.*;
import lombok.extern.slf4j.Slf4j;
import net.seehope.crowd.constant.CrowdConstant;
import net.seehope.crowd.util.JSONResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/10 23:07
 * @Version 1.0
 * 1.收集发起项目信息
 * 2.收集项目发起回报信息
 * 3.收集确认支付账号密码
 * 4.点击主页分类项目跳转到显示项目详细信息页面
 */
@Controller
@Slf4j
public class ProjectConsumerController {

    // cos存储配置类
    @Autowired
    private COSPropertiesConfig propertiesConfig;

    // 创建一个集合装载detailPictureList详情图片
    List<String> detailPictureListList = new ArrayList<>();

    @Autowired
    private MySQLRemoteService mySQLRemoteService;


    // 4.点击主页分类项目跳转到显示项目详细信息页面
    @RequestMapping("/click/into/projectdetail/{projectId}")
    public String clickIntoProjectDetail(@PathVariable("projectId") Integer projectId , ModelMap modelMap){
        JSONResult<DetailProjectVO> clickProjectDetailInfoRemote = mySQLRemoteService.getClickProjectDetailInfoRemote(projectId);
        if (JSONResult.SUCCESS.equals(clickProjectDetailInfoRemote.getResult())){
            // 由于mybatis查询出来的是一个map类型，所以要引入com.alibaba.fastjson.JSON的包进行转换
            String s = JSON.toJSONString(clickProjectDetailInfoRemote.getData());

            DetailProjectVO detailProjectVO = JSON.parseObject(s,DetailProjectVO.class);

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_SHOW_PROJECT_DETAIL,detailProjectVO);
            return "project-detail";
        }

        return null;
    }



    // 3.收集确认支付账号密码
    @RequestMapping("/confirm/project/submit")
    public String projectConfirm(HttpSession httpSession,
                                             MemberConfirmInfoVO memberConfirmInfoVO,
                                             ModelMap modelMap){

        // 1.从session域中取出projectVO对象
        ProjectVO projectVO = (ProjectVO) httpSession.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);

        // 2.判断取出的project是否为空
        if (projectVO == null){
            throw new RuntimeException(CrowdConstant.MESSAGE_TEMP_PROJECT_MISSING);
        }


        // 3.从session域中获取当前登录Member对象
        MemberLoginVO memberLoginVO = (MemberLoginVO) httpSession.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN_SESSION);


        // 4.获取当前Member对象的id
        Integer id = memberLoginVO.getId();


        // 5.将确认信息数据设置到projectVO对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 6.调用远程方法保存projectVO对象
        JSONResult<String> jsonResult = mySQLRemoteService.saveProjectVORemote(projectVO,id);


        // 7.将临时的projectVO从session域中移除
        httpSession.removeAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);

        // 8.判断调用远程方法是否成功
        String result = jsonResult.getResult();
        if (!JSONResult.SUCCESS.equals(result)){
            modelMap.addAttribute(CrowdConstant.MESSAGE_CODE_ERROR,jsonResult.getMessage());
            return "project-confirm";
        }
        return "redirect:http://localhost:9000/project/create/success";
    }

    // 2.收集项目发起回报信息
    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public JSONResult<String> saveReturnInfo(ReturnVO returnVO ,HttpSession httpSession){
        try {
            // 1.从session域中读取之前缓存的projectVO信息
            ProjectVO projectVO = (ProjectVO) httpSession.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);

            // 2.判断projectVO是否为null
            if (projectVO == null){
                return JSONResult.FailureNeedMessage(CrowdConstant.MESSAGE_TEMP_PROJECT_MISSING);
            }

            // 3.从projectVO对象中获取存储回报信息的集合
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();

            // 4.判断returnVOList集合是否有效
            if (returnVOList == null || returnVOList.size() == 0){
    
                // 注意projectVO中的returnVOList还没有初始化
                // 5.创建集合对象对returnVOList进行初始化
                returnVOList = new ArrayList<>();
    
                // 6.为了让以后能够正常使用这个集合，设置到projectVO对象中
                projectVO.setReturnVOList(returnVOList);
    
            }
            // 7.将收集了表单数据的returnVO对象存入集合
            returnVOList.add(returnVO);
            projectVO.setReturnVOList(returnVOList);

            // 8.把数据有变化的ProjectVO对象重新存入session域中，以确保新的数据最终能够存入Redis
            httpSession.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT,projectVO);
            
            return JSONResult.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();

            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }


    // js的formData.append("returnPicture", file);
    // returnPicture是请求参数的名字
    // file是请求参数的值，也就是要上传的文件
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public JSONResult<String> uploadReturnPicture(@RequestParam("returnPicture") MultipartFile multipartFile){
        JSONResult<String> stringJSONResult = UploadUtils.doUpload(propertiesConfig.getAccesskey(), propertiesConfig.getSecretKey(),
                propertiesConfig.getBucket(), propertiesConfig.getBucketName(), propertiesConfig.getPath(), multipartFile);
        UploadMsg uploadMsg = (UploadMsg) stringJSONResult.getData();
        String path = uploadMsg.getPath();
        stringJSONResult.setData(path);

        return stringJSONResult;
    }



    // 1.收集发起项目信息
    // ProjectVO projectVO接收除了上传图片之外的其他数据
    // MultipartFile headerPicture接收上传的头图
    // List<MultipartFile> detailPictureList 接收上传的详情图片
    // 用来将收集了一部分数据的ProjectVO对象存入Session域
    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(ProjectVO projectVO,
                                       MultipartFile headerPicture,
                                       List<MultipartFile> detailPictureList,
                                       HttpSession httpSession,
                                       ModelMap modelMap
                                       ){

        // 1.获取头图判断是否为空
        boolean empty = headerPicture.isEmpty();

        // 2.为空就跳转到项目发起原页面显示错误消息
        if (empty){
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,CrowdConstant.MESSAGE_HERDER_PIC_UPLOAD_FAILURE);
                return "project-launch";
            }else {

            // 3.不为空就上传图片
            JSONResult<String> stringJSONResult = UploadUtils.doUpload(propertiesConfig.getAccesskey(), propertiesConfig.getSecretKey(),
                    propertiesConfig.getBucket(), propertiesConfig.getBucketName(), propertiesConfig.getPath(), headerPicture);
            String result = stringJSONResult.getResult();

            // 4.判断头像是否上传成功
            if (JSONResult.SUCCESS.equals(result)){

                // 5.从返回数据中获取图片访问路径
                UploadMsg uploadMsg = (UploadMsg) stringJSONResult.getData();
                String path = uploadMsg.getPath();

                // 6.存入ProjectVO对象中
                projectVO.setHeaderPicturePath(path);
            }
        }
        
        // 7.如果详情图片为空就携带错误消息跳转到项目发起页面显示错误消息
        if (detailPictureList.isEmpty()){
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILURE);
            return "project-launch";
        }else {
            // 8.遍历detailPictureList集合
            for (MultipartFile detailFile : detailPictureList) {
                JSONResult<String> stringJSONResult = UploadUtils.doUpload(propertiesConfig.getAccesskey(), propertiesConfig.getSecretKey(),
                        propertiesConfig.getBucket(), propertiesConfig.getBucketName(), propertiesConfig.getPath(), detailFile);

                // 9.检查详情图片上传结果，如果上传失败就携带错误消息跳转到项目发起页面显示错误消息
                if (!JSONResult.SUCCESS.equals(stringJSONResult.getResult())){
                    modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILURE_TRY_AGAIN);
                    return "project-launch";
                }

                // 10.上传详情图片
                UploadMsg uploadMsg = (UploadMsg) stringJSONResult.getData();

                String path = uploadMsg.getPath();

                // 11.收集刚刚上传图片访问地址
                detailPictureListList.add(path);
            }
        }
        // 12.将存放了详情图片访问路劲的集合存入projectVO中
        projectVO.setDetailPicturePathList(detailPictureListList);
        
        // 13.将ProjectVO对象存入Session域
        httpSession.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT,projectVO);

        // 14.以完整的访问路径前往下一个手机回报信息的页面
        return "redirect:http://localhost:9000/project/return/project/page";
    }
}
