package com.yang.mapper;

import com.yang.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    // where
    List<User> getUsers(@Param("username") String username, @Param("password") String password);

    // trim
    List<User> getUserList(@Param("username") String username, @Param("password") String password);

    // choose
    List<User> getUserChoose(@Param("username") String username, @Param("password") String password);

    // forEach， 这个可以传入数组，列表，pojo对象也可以，只要是列表类的就行
    List<User> getUserByIds(@Param("idList") Integer[] ids);

    // set 与 bind
    void updateUser(User user);

    // sql 与include语句
    User getUserById(@Param("id") Integer id);
    User getUser(@Param("id") Integer id);
}
