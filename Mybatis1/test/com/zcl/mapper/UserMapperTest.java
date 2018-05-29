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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018-05-15.
 */
public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        String resouce = "SqlMapConfig.xml";

        InputStream inputStream = Resources.getResourceAsStream(resouce);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //通过id来查找user
    @Test
    public void testFindUserById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //User user = userMapper.selectByPrimaryKey(10);
            //通过name来查找
            User user = userMapper.selectByUserName("亮亮");
            //User user = userMapper.selectByUserName("%军%");

            System.out.println("通过Usermapper查找出来的--->"+user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlSession){
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //通过name来查找user
    @Test
    public void testFindUserByName(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            UserExample userExample = new UserExample();

            userExample.setOrderByClause("id");

            UserExample.Criteria criteria = userExample.createCriteria();
            //criteria.andUsernameNotEqualTo("亮亮");
            criteria.andUsernameLike("%"+"明"+"%");

            List<User> users = userMapper.selectByExample(userExample);

            for (int i = 0; i < users.size(); i++) {
                User user =  users.get(i);
                System.out.println("根据username来排序--->"+user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlSession){
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFindUserByHashmap()throws Exception{
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //构造查询条件Hashmap对象
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("username", "明");

        //传递Hashmap对象查询用户列表
        List<User> list = userMapper.findUserByHashmap(map);
        //关闭session
        session.close();
    }

    @Test
    public void testFindUserListResultMap() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        QueryVo queryVo = new QueryVo();
        UserCustom userCustom = new UserCustom();

        userCustom.setUsername("张");
        //传入多个id
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(10);
        ids.add(16);

        queryVo.setIds(ids);
        queryVo.setUserCustom(userCustom);

        List<User> userListResultMap = userMapper.findUserListResultMap(queryVo);

        for (int i = 0; i < userListResultMap.size(); i++) {
            User user =  userListResultMap.get(i);
            System.out.println("根据多个id来查找用户,现在为第"+i+"----->"+user);
        }
    }

    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = new ArrayList();

        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(27);

        list.add(user1);
        list.add(user2);

        List<User> userList = userMapper.findUserListByList(list);

        for (int i = 0; i < userList.size(); i++) {
            User user =  userList.get(i);
            System.out.println("通过list查找出来的用户"+user);
        }
    }

    @Test
    public void testFindOrderAndUserCustom() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<OrderAndUserCustom> list = userMapper.findOrderAndUserCustom();

        for (int i = 0; i < list.size(); i++) {
            OrderAndUserCustom orderAndUser =  list.get(i);
            System.out.println("通过orderandUser查找出来的--->"+orderAndUser);
        }
    }

}
