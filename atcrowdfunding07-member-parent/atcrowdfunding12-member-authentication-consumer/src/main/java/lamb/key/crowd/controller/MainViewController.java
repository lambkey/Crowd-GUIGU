package lamb.key.crowd.controller;

import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.vo.PortalTypeVO;
import net.seehope.crowd.constant.CrowdConstant;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/1 2:11
 */
@Controller
public class MainViewController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String portal(ModelMap modelMap){
        //这里是实际开发中需要开发的数据(显示主页数据)... ...
        JSONResult<List<PortalTypeVO>> typeAndProjectDetailRemote = mySQLRemoteService.getTypeAndProjectDetailRemote();
        List<PortalTypeVO> data = (List<PortalTypeVO>) typeAndProjectDetailRemote.getData();
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_TYPE_PROJECT_DETAIL,data);
        return "main";
    }
}
