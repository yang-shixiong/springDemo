package com.yang.test;

import com.yang.Transaction.ProxyUser;
import com.yang.Transaction.Transaction;
import com.yang.UserService.Impl.UserServiceImpl;
import com.yang.UserService.UserService;
import com.yang.bean.User;

public class TestUser {

    // 没有嗲用TEST框架，直接使用main来调用
    public static void main(String[] args) {
        Transaction ts = new Transaction();
        UserService us = new UserServiceImpl();

        // 这个就是我们实例化出来的代理对象，可以看出跟以前的对象是不同的以前的对象是new UserServiceImpl()，接受都是使用UserService接口，这不就是多态
        UserService pu = new ProxyUser(us, ts);

        pu.addUser(new User(1, "ming"));
        pu.deleteUser(1);

    }
}
