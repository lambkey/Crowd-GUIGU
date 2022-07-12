package lamb.key.crowd.service;

import lamb.key.crowd.po.MemberPO;

/**
 * @author JoinYang
 * @date 2022/5/28 17:40
 */
public interface ServiceMemberPO {

    MemberPO getMemberPOByLoginAcct(String loginacct);

    int saveMemberPOInfo(MemberPO memberPO);

}
