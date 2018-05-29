package com.zcl.dao;

import com.zcl.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by Administrator on 2018-05-15.
 */
public class UserDaoImpl implements UserDao{
    private SqlSessionFactory sqlSessionFactory;

    //注入SqlSessionFactory
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User testFindById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = null;
        try {
            user = sqlSession.selectOne("test.findUserById", 10);

            System.out.println("id为10的用户是"+user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> testFindByUsername(String username) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> listUser = null;
        try {
            listUser = sqlSession.selectList("test.findUserByUsername", username);

            /*for (int i = 0; i < listUser.size(); i++) {
                User user1 =  listUser.get(i);
                System.out.println("这是第"+i+"个用户---》"+user1);
            }*/
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

        return listUser;
    }

}
