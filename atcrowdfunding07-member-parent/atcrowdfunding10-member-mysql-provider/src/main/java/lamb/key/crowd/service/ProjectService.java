package lamb.key.crowd.service;

import lamb.key.crowd.vo.DetailProjectVO;
import lamb.key.crowd.vo.PortalTypeVO;
import lamb.key.crowd.vo.ProjectVO;
import net.seehope.crowd.util.JSONResult;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/10 14:09
 * @Version 1.0
 */
public interface ProjectService {

    DetailProjectVO getDetailProject(Integer projectId);

    List<PortalTypeVO> getPortalTypeVO();

    JSONResult saveProject(ProjectVO projectVO, Integer id);


}
