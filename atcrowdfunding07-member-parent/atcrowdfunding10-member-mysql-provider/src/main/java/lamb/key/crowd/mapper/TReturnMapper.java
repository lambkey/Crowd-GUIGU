package lamb.key.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.crowd.po.ReturnPO;
import org.apache.ibatis.annotations.Mapper;
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

public interface TReturnMapper extends BaseMapper<ReturnPO> {

    void saveReturnInfo(@Param("returnPOList") List<ReturnPO> returnPOList,
                        @Param("projectId") Integer projectId);
}
