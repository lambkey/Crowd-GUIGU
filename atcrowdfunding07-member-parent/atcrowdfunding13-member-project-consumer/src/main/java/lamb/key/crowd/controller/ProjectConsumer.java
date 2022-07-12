package lamb.key.crowd.controller;

import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.po.MemberPO;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/5/28 18:26
 */
@RestController
public class ProjectConsumer {
    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/get/member")
    public JSONResult<MemberPO> getMemberPO(@RequestParam("loginacct") String loginacct){

       JSONResult<MemberPO> result = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
       return result;
    }
}
