package com.yang.test;

import com.yang.Transaction.ObjectInterceptor;
import com.yang.Transaction.Transaction;
import com.yang.Uservice.Impl.UserServiceImpl;
import com.yang.Uservice.UserService;
import com.yang.bean.User;

import java.lang.reflect.Proxy;

public class TestUser {

    public static void main(String[] args) {
        // 目标类
        Object target = new UserServiceImpl();
        // 我们的事务类
        Transaction transaction = new Transaction();

        // 代理类
        ObjectInterceptor objectInterceptor = new ObjectInterceptor(target, transaction);

        // 使用Proxy.newProxyInstance为我们返回代理类，从参数可以看出，他必须要实现接口，否则无法传值* Returns a proxy instance for the specified interfaces
        UserService userService = (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), objectInterceptor);
        userService.addUser(new User(1,"ming"));
        userService.deleteUser(1);

    }
}
