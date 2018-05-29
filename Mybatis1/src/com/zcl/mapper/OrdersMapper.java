package com.zcl.mapper;

import com.zcl.po.OrderAndUserCustom;
import com.zcl.po.Orders;
import com.zcl.po.OrdersExample;
import com.zcl.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<OrderAndUserCustom> findOrderAndUserCustom();

    List<Orders> findOrderListResultMap();

    List<Orders> findOrdersDetailList();

    List<User> findOrderItems();

    List<Orders> findOrdersList();
}