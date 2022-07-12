package lamb.key.crowd.service;

import lamb.key.crowd.vo.AddressVO;
import lamb.key.crowd.vo.OrderProjectVO;
import lamb.key.crowd.vo.OrderVO;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/1 12:31
 * @Version 1.0
 */
public interface OrderService {
    OrderProjectVO selectOrderProjectVO(Integer projectId,Integer returnId);

    List<AddressVO> getMemberAddressByMemberId(Integer id);

    int saveAddressVO(AddressVO addressVO);

    void saveOrderVO(OrderVO orderVO);
}
