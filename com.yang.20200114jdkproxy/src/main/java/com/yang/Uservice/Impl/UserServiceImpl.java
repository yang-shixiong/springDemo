package com.yang.Uservice.Impl;

import com.yang.Uservice.UserService;
import com.yang.bean.User;

// 基本业务层代码，没啥好说的
public class UserServiceImpl implements UserService {
    public void addUser(User user) {
        System.out.println("will add a user" + user);
    }

    public void deleteUser(int userID) {
        System.out.println("will delete user:" + userID);
    }
}
