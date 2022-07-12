package lamb.key.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.crowd.po.MemberPO;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-05-28
 */

public interface MemberPOMapper extends BaseMapper<MemberPO> {
    /*@Select("SELECT * FROM t_admin where login_acct=#{login_acct}")
    Admin getAdminByLogAcct(@Param("login_acct") String login_acct);*/

    // 根据会员账号查询会员的完整信息
    @Select("SELECT * FROM T_MEMBER WHERE LOGINACCT=#{loginacct}")
    MemberPO  getMemberPOByLoginAcct(@RequestParam("loginacct") String loginacct);
}
