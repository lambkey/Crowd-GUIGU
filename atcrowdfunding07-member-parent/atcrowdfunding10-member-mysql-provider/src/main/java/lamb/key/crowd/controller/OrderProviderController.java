package lamb.key.crowd.controller;

import lamb.key.crowd.service.OrderService;
import lamb.key.crowd.vo.AddressVO;
import lamb.key.crowd.vo.OrderProjectVO;
import lamb.key.crowd.vo.OrderVO;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/1 13:10
 * @Version 1.0
 * 1.点击支持显示回报信息和购买数量
 */
@RestController
public class OrderProviderController {

    @Autowired
    private OrderService orderService;

    // 4.保存最终的订单消息，包括整个orderProjectPO，orderPO
    @RequestMapping("/save/orderVO")
    public JSONResult<String> saveOrderVO(@RequestBody OrderVO orderVO){
        try {
            orderService.saveOrderVO(orderVO);
            return JSONResult.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }


    // 3.保存用户新增的地址
    @RequestMapping("/save/address/remote")
    public JSONResult<String> saveAddressVO(@RequestBody AddressVO addressVO){
        try {
            int i = orderService.saveAddressVO(addressVO);
            return JSONResult.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }

    // 2.用户填写回报数量，去结算，要显示已经填报的地址
    @RequestMapping("/get/address/remote")
    public JSONResult<List<AddressVO>> getAddressRemote(@RequestParam("id") Integer id){

        try {
            List<AddressVO> memberAddressByMemberId = orderService.getMemberAddressByMemberId(id);
            return JSONResult.successNeedData(memberAddressByMemberId);
        } catch (Exception e) {
            e.printStackTrace();
           return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }

    // 1.点击支持显示回报信息和购买数量
    @RequestMapping("/get/confirm/return/info/remote")
    public JSONResult<OrderProjectVO> getConfirmReturnInfo(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId){

        try {
            OrderProjectVO orderProjectVO = orderService.selectOrderProjectVO(projectId, returnId);

            return JSONResult.successNeedData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }
}
