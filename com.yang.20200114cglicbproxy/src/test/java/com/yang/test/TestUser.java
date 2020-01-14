package com.yang.test;

import yang.Transaction.CglibProxy;
import yang.Transaction.Transaction;
import yang.Uservice.Impl.UserServiceImpl;
import yang.bean.User;

public class TestUser {
    public static void main(String[] args) {
        // 实例化增强类
        Transaction transaction = new Transaction();
        // 创建我们的代理类
        CglibProxy cglibProxy = new CglibProxy(transaction);
        // 调用代理类方法，生成代理对象，从这里面可以看出，我们是直接使用类进行实现的，没有用到接口
        UserServiceImpl userService = (UserServiceImpl) cglibProxy.getProxy(UserServiceImpl.class);
        userService.addUser(new User(1, "ming"));
        userService.deleteUser(1);
    }
}
