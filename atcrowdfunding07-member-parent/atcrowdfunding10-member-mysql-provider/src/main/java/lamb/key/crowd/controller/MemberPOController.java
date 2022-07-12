package lamb.key.crowd.controller;

import lamb.key.crowd.po.MemberPO;
import lamb.key.crowd.service.ServiceMemberPO;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/5/28 17:29
 * 会员登录
 *
 * 会员注册
 */
@RestController
public class MemberPOController {
    @Autowired
    private ServiceMemberPO serviceMemberPO;

    // 根据会员账号查询会员的完整信息
    @RequestMapping("/get/member/po/by/login/acct/remote")
   public JSONResult<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){

        // 调用本地service完成查询
        MemberPO memberPO=serviceMemberPO.getMemberPOByLoginAcct(loginacct);
        try {
            // 没有异常就放回结果
            return JSONResult.successNeedData(memberPO);
        }catch (Exception e){
            // 出现异常就返回异常消息
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }

    // 插入会员信息
    @RequestMapping("/save/member/po/info/remote")

    public JSONResult<String> saveMemberPOInfoRemote(@RequestBody MemberPO memberPO){
        try {
            serviceMemberPO.saveMemberPOInfo(memberPO);
            return JSONResult.successWithoutData();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                return JSONResult.FailureNeedMessage("用户已存在");
            }
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }

}
