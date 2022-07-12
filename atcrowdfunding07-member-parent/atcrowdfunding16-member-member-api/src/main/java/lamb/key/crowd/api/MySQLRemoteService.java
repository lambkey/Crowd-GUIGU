package lamb.key.crowd.api;

import lamb.key.crowd.po.MemberPO;
import lamb.key.crowd.vo.*;
import net.seehope.crowd.util.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/5/28 17:22
 */
@FeignClient(value = "lambkey-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/get/member/po/by/login/acct/remote")
    JSONResult<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping("/save/member/po/info/remote")
    public JSONResult<String> saveMemberPOInfoRemote(@RequestBody MemberPO memberPO);

    @RequestMapping("/save/project/to/remote")
    public JSONResult<String> saveProjectVORemote(@RequestBody ProjectVO projectVO,@RequestParam("id") Integer id);


    @RequestMapping("/get/project/type/and/project/detail")
    public JSONResult<List<PortalTypeVO>> getTypeAndProjectDetailRemote();

    @RequestMapping("/get/click/project/detail/info/{projectId}")
    public JSONResult<DetailProjectVO> getClickProjectDetailInfoRemote(@PathVariable("projectId") Integer projectId);

    // 订单模块
    @RequestMapping("/get/confirm/return/info/remote")
    JSONResult<OrderProjectVO> getConfirmReturnInfo(@RequestParam("projectId") Integer projectId,@RequestParam("returnId") Integer returnId);

    @RequestMapping("/get/address/remote")
    JSONResult<List<AddressVO>> getAddressRemote(@RequestParam("id") Integer id);

    @RequestMapping("/save/address/remote")
    JSONResult<String> saveAddressVO(@RequestBody AddressVO addressVO);

    @RequestMapping("/save/orderVO")
    JSONResult<String> saveOrderVO(@RequestBody OrderVO orderVO);
}
