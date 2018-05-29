package com.zcl.first;

import com.zcl.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2018-05-11.
 */
public class MybatisFirst {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlsession = null;

    @Before
    public void createSqlSessionFactory() throws IOException {
        String resouce = "SqlMapConfig.xml";

        InputStream inputStream = Resources.getResourceAsStream(resouce);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    // 根据 id查询用户信息
    @Test
    public void testFindById(){


        try {
            sqlsession = sqlSessionFactory.openSession();
            User user = sqlsession.selectOne("test.findUserById",10);

            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlsession){
                try {
                    sqlsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 根据用户名称模糊查询用户信息
    @Test
    public void testFindByUsername(){

        try {
            sqlsession = sqlSessionFactory.openSession();

            List<User> list = sqlsession.selectList("test.findUserByUsername","明");

            for (int i = 0; i < list.size(); i++) {
                User user =  list.get(i);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlsession){
                try {
                    sqlsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //查询用户是男生的 2
    @Test
    public void testFindBySex(){
        try {
            sqlsession = sqlSessionFactory.openSession();

            List<User> listUser = sqlsession.selectList("test.findUserByAge",2);

            for (int i = 0; i < listUser.size(); i++) {
                User user =  listUser.get(i);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlsession){
                try {
                    sqlsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //插入一条数据
    @Test
    public void testInsertUser() {
        try {
            sqlsession = sqlSessionFactory.openSession();

            User user = new User();

            //user.setId(102);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            System.out.println(date +  "*******时间*********" + format);
            user.setBirthday(date);
            user.setUsername("插入数据测试代码");
            user.setSex("2");
            user.setAddress("不知从何处来");

            int insert = sqlsession.insert("test.insertUser", user);

            if (insert > 0) {
                System.out.println("插入成功！");
            }

            sqlsession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != sqlsession) {
                try {
                    sqlsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //删除一条数据
    @Test
    public void testDeleteUser(){
        try {
            sqlsession = sqlSessionFactory.openSession();

            int count = sqlsession.delete("test.deleteUser", 103);

            if (count > 0){
                System.out.println("删除成功！");
            }

            sqlsession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != sqlsession){
                try {
                    sqlsession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //修改一条数据
    @Test
    public void testUpdateUser(){
        try {
            sqlsession = sqlSessionFactory.openSession();

            User user = new User();
            user.setUsername("亮亮");
            user.setAddress("常宁");
            Date date = new Date();
            user.setBirthday(date);

            user.setId(26);

            int update = sqlsession.update("test.updateUserById", user);

            if (update > 0){
                System.out.println("修改成功！");
            }

            sqlsession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                sqlsession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
