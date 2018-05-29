package com.zcl.dao;

import com.zcl.po.User;

import java.util.List;

/**
 * Created by Administrator on 2018-05-15.
 */
public interface UserDao {

    public User testFindById(int id) throws Exception;

    public List<User> testFindByUsername(String username) throws Exception;
}
