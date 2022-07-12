package lamb.key.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.crowd.po.MemberConfirmInfoPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author lamb
 * @since 2022-06-10
 */

public interface TMemberConfirmInfoMapper extends BaseMapper<MemberConfirmInfoPO> {
    @Insert("insert into t_member_confirm_info(memberid,paynum,cardnum) values (#{memberConfirmInfoPO.memberid},#{memberConfirmInfoPO.paynum},#{memberConfirmInfoPO.cardnum})")
    void saveMemberConfirmInfo(@Param("memberConfirmInfoPO") MemberConfirmInfoPO memberConfirmInfoPO);
}
