package com.zcl.mapper;

import com.zcl.po.OrderAndUserCustom;
import com.zcl.po.QueryVo;
import com.zcl.po.User;
import com.zcl.po.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String name);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findUserByHashmap(HashMap<String,Object> map);

    List<User> findUserListResultMap(QueryVo queryVo);

    List<User> findUserListByList(List<User> list);

    List<OrderAndUserCustom> findOrderAndUserCustom();

}