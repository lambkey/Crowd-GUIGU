package lamb.key.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.crowd.po.MemberLaunchInfoPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-06-10
 */

public interface TMemberLaunchInfoMapper extends BaseMapper<MemberLaunchInfoPO> {
    @Insert("INSERT INTO T_MEMBER_LAUNCH_INFO(memberid,description_simple,description_detail,phone_num,service_num)values(#{memberLaunchInfoPO.memberid},#{memberLaunchInfoPO.descriptionSimple},#{memberLaunchInfoPO.descriptionDetail},#{memberLaunchInfoPO.phoneNum},#{memberLaunchInfoPO.serviceNum})")
    void saveMemberLaunchInfo(@Param("memberLaunchInfoPO") MemberLaunchInfoPO memberLaunchInfoPO);
}
