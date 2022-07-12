package lamb.key.crowd.service.imp;

import lamb.key.crowd.mapper.MemberPOMapper;
import lamb.key.crowd.po.MemberPO;
import lamb.key.crowd.service.ServiceMemberPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JoinYang
 * @date 2022/5/28 17:39
 * 会员的登录验证
 *
 * 会员注册信息
 */
@Service
// 在类上使用@Transactionl(readOnly = true)针对查询操作设置事务属性，只读
@Transactional(readOnly = true)
public class ImpServiceMemberPO implements ServiceMemberPO {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {

        return memberPOMapper.getMemberPOByLoginAcct(loginacct);
    }


    // 注册保存会员消息
    // 设置事务不为只读
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public int saveMemberPOInfo(MemberPO memberPO) {
        return memberPOMapper.insert(memberPO);
    }
}
