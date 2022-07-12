package lamb.key.crowd.controller;

import lamb.key.crowd.service.ProjectService;
import lamb.key.crowd.vo.DetailProjectVO;
import lamb.key.crowd.vo.PortalTypeVO;
import lamb.key.crowd.vo.ProjectVO;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/10 14:03
 * @Version 1.0
 *
 * 1.会员众筹项目信息的插入
 * 2.主页项目的展示
 * 3.项目详情展示
 */
@RestController
public class ProjectProviderController {

    @Autowired
    private ProjectService projectService;

    // 1.会员众筹项目信息的插入
    @RequestMapping("/save/project/to/remote")
    public JSONResult<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("id") Integer id){
        try{
            projectService.saveProject(projectVO,id);
            return JSONResult.successWithoutData();
        }catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }

    // 2.主页项目分类的展示
    @RequestMapping("/get/project/type/and/project/detail")
    public JSONResult<List<PortalTypeVO>> getTypeAndProjectDetailRemote(){

        try {
            return JSONResult.successNeedData(projectService.getPortalTypeVO());
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }

    // 3.项目详情展示
    @RequestMapping("/get/click/project/detail/info/{projectId}")
    public JSONResult<DetailProjectVO> getClickProjectDetailInfoRemote(@PathVariable("projectId") Integer projectId){

        try {
            DetailProjectVO detailProject = projectService.getDetailProject(projectId);
            return  JSONResult.successNeedData(detailProject);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }



}
