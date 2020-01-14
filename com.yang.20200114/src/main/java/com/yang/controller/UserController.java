package com.yang.controller;

import com.yang.bean.User;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller  // controller层的通知spring来进行扫描的，功能与component一样
public class UserController {

    @Autowired// 自动加载相关对象
    UserService uc;

    public void getUsers() {
        List<User> userList = uc.getUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
