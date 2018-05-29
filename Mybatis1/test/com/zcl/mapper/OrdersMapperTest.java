package com.zcl.mapper;

import com.zcl.po.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018-05-16.
 */
public class OrdersMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        String resouce = "SqlMapConfig.xml";

        InputStream inputStream = Resources.getResourceAsStream(resouce);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindOrderAndUserCustomAtOrder() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<OrderAndUserCustom> orderAndUserCustom = ordersMapper.findOrderAndUserCustom();

        for (int i = 0; i < orderAndUserCustom.size(); i++) {
            OrderAndUserCustom andUserCustom =  orderAndUserCustom.get(i);
            System.out.println("在order下面找到的"+andUserCustom);
        }
    }

    @Test
    public void testFindOrderListResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<Orders> listResultMap = ordersMapper.findOrderListResultMap();

        for (int i = 0; i < listResultMap.size(); i++) {
            Orders orders =  listResultMap.get(i);
            System.out.println("通过order来一对一查找"+orders);
        }
    }

    @Test
    public void testFindOrdersDetailList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<Orders> ordersDetailList = ordersMapper.findOrdersDetailList();

        for (int i = 0; i < ordersDetailList.size(); i++) {
            Orders orders =  ordersDetailList.get(i);
            System.out.println("查找order的详情"+ orders);
        }

    }

    @Test
    public void testFindOrderItems() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<User> orderItems = ordersMapper.findOrderItems();

        //System.out.println(orderItems.size()+"+++++++++++++++++"+orderItems);

        for (int i = 0; i < orderItems.size(); i++) {
            List<Orders> orders = orderItems.get(i).getOrders();
            for (int j = 0; j < orders.size(); j++) {
                List<Orderdetail> orderdetails = orders.get(j).getOrderdetails();
                System.out.println("orderdetails"+orderdetails);
                for (int k = 0; k < orderdetails.size(); k++) {
                    Items items = orderdetails.get(k).getItems();
                    System.out.println("所有的items"+items);
                }
            }
        }
    }

    @Test
    public void testFindOrdersList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        long time1 = new Date().getTime();

        List<Orders> ordersList = ordersMapper.findOrdersList();

        for (int i = 0; i < ordersList.size(); i++) {
            Orders orders =  ordersList.get(i);
            System.out.println("显示order的数据"+orders);
            User user = orders.getUser();
            System.out.println("user的信息为"+user);
            /*Integer id = orders.getUserId();
            User user = userMapper.selectByPrimaryKey(id);
            System.out.println("通过orders查找出的id来查找的user为"+user);*/
        }

        long time2 = new Date().getTime();

        System.out.println("相差"+(time2-time1));
    }
}
