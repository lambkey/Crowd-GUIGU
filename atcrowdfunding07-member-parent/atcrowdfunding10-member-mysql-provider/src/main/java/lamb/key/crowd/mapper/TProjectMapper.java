package lamb.key.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.crowd.po.ProjectPO;
import lamb.key.crowd.vo.DetailProjectVO;
import lamb.key.crowd.vo.PortalTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-06-10
 */

public interface TProjectMapper extends BaseMapper<ProjectPO> {

   DetailProjectVO selectDetailProjectVO(Integer projectId);

   List<PortalTypeVO> selectPortalTypeVOList();

   void insertSelective( ProjectPO projectPO);

   void insertTypeRelationship(@Param("typeIdList") List<Integer> typeIdList,
                               @Param("projectId") Integer projectId);

   void insertTagRelationship(@Param("tagIdList") List<Integer> tagIdList,
                              @Param("projectId") Integer projectId);
}
