package lamb.key.crowd.controller;

import com.alibaba.fastjson2.JSON;
import lamb.key.crowd.api.MySQLRemoteService;
import lamb.key.crowd.vo.AddressVO;
import lamb.key.crowd.vo.MemberLoginVO;
import lamb.key.crowd.vo.OrderProjectVO;
import net.seehope.crowd.constant.CrowdConstant;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/1 11:57
 * @Version 1.0
 *
 */
@Controller
public class OrderConsumerController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    // 3.填写收货地址点击保存地址消息重定向到订单确认页面
    @RequestMapping("/save/address")
    public String saveAddress( AddressVO addressVO,HttpSession httpSession){
        JSONResult<String> flag = mySQLRemoteService.saveAddressVO(addressVO);
        OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute("orderProjectVO");
        Integer returnCount = orderProjectVO.getReturnCount();
        return "redirect:http://localhost:9000/order/confirm/order/"+returnCount;
    }

    // 2.点击结算跳转到订单确认页面
    @RequestMapping("/confirm/order/{returnCount}")
    public String confirmOrderInfo(@PathVariable("returnCount") Integer returnCount,HttpSession httpSession){

        // ①把接收到的回报数量合并到Session域
        OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute("orderProjectVO");
        orderProjectVO.setReturnCount(returnCount);
        httpSession.setAttribute("orderProjectVO",orderProjectVO);

        // ②获取已登录用户的id
        MemberLoginVO memberVO = (MemberLoginVO) httpSession.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN_SESSION);
        Integer id = memberVO.getId();

        // ③查询目前的收获地址
        JSONResult<List<AddressVO>> addressRemote = mySQLRemoteService.getAddressRemote(id);
        if (JSONResult.SUCCESS.equals(addressRemote.getResult())){
            List<AddressVO> list = (List<AddressVO>) addressRemote.getData();
            httpSession.setAttribute("addressVOList",list);
        }
        return "confirm-order";
    }

    // 1.点击支持显示回报信息
    @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
    public String confirmReturnInfo(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId, HttpSession httpSession){

        JSONResult<OrderProjectVO> confirmReturnInfo = mySQLRemoteService.getConfirmReturnInfo(projectId, returnId);

        if (JSONResult.SUCCESS.equals(confirmReturnInfo.getResult())){
            String s = JSON.toJSONString(confirmReturnInfo.getData());
            OrderProjectVO orderProjectVO = JSON.parseObject(s, OrderProjectVO.class);
            // 为了后续操作能够保持orderProjectVO的数据，存入session域
            httpSession.setAttribute("orderProjectVO",orderProjectVO);
        }

        return "confirm-return";

    }
}
