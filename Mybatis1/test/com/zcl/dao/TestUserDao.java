package com.zcl.dao;

import com.zcl.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


/**
 * Created by Administrator on 2018-05-15.
 */
public class TestUserDao {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        // 创建sqlSessionFactory

        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void testFindUserById(){
//            SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDaoImpl userDao = new UserDaoImpl(sqlSessionFactory);

            User user = userDao.testFindById(10);

            System.out.println("10号id的用户查询成功--->"+user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFindUserByUsername(){
        try {
            UserDaoImpl userDao = new UserDaoImpl(sqlSessionFactory);

            List<User> userList = userDao.testFindByUsername("明");

            for (int i = 0; i < userList.size(); i++) {
                User user =  userList.get(i);
                System.out.println("这是第"+i+"个用户---》"+user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
