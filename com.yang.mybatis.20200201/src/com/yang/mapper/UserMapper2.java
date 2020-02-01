package com.yang.mapper;

import com.yang.domain.User;

import java.util.List;

public interface UserMapper2 {

    User getUserById(Integer id);

    void insertUser(User user);

    List<User> getUsers();
}
