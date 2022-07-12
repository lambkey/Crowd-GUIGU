package lamb.key.crowd.service.imp;

import lamb.key.crowd.mapper.AddressPOMapper;
import lamb.key.crowd.mapper.OrderPOMapper;
import lamb.key.crowd.mapper.OrderProjectPOMapper;
import lamb.key.crowd.po.AddressPO;
import lamb.key.crowd.po.OrderPO;
import lamb.key.crowd.po.OrderProjectPO;
import lamb.key.crowd.service.OrderService;
import lamb.key.crowd.vo.AddressVO;
import lamb.key.crowd.vo.OrderProjectVO;
import lamb.key.crowd.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/1 12:26
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;
    @Override
    public OrderProjectVO selectOrderProjectVO(Integer projectId, Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getMemberAddressByMemberId(Integer id) {
        return orderProjectPOMapper.getMemberAddressByMemberId(id);
    }


    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    public int saveAddressVO(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);
        return addressPOMapper.insert(addressPO);
    }



    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    @Override
    public void saveOrderVO(OrderVO orderVO) {
        OrderProjectVO orderProjectVO =orderVO.getOrderProjectVO();

        OrderPO orderPO =new OrderPO();

        BeanUtils.copyProperties(orderVO,orderPO);

        orderPOMapper.insert(orderPO);

        Integer id = orderPO.getId();

        orderProjectVO.setOrderId(id);

        OrderProjectPO orderProjectPO = new OrderProjectPO();

        BeanUtils.copyProperties(orderProjectVO,orderProjectPO);

        orderProjectPOMapper.insert(orderProjectPO);
    }

}
