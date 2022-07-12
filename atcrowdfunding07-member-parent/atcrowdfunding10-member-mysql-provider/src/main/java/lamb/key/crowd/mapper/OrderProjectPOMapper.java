package lamb.key.crowd.mapper;

import lamb.key.crowd.po.OrderProjectPO;
import lamb.key.crowd.po.OrderProjectPOExample;
import lamb.key.crowd.vo.AddressVO;
import lamb.key.crowd.vo.OrderProjectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProjectPOMapper {
    int countByExample(OrderProjectPOExample example);

    int deleteByExample(OrderProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProjectPO record);

    int insertSelective(OrderProjectPO record);

    List<OrderProjectPO> selectByExample(OrderProjectPOExample example);

    OrderProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByExample(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByPrimaryKeySelective(OrderProjectPO record);

    int updateByPrimaryKey(OrderProjectPO record);
    OrderProjectVO selectOrderProjectVO(@Param("returnId") Integer returnId);

    List<AddressVO> getMemberAddressByMemberId(@Param("id") Integer id);
}