package com.yang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yang.UserService.UserService;
import yang.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUser {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        userService.addUser(new User(1, "ming"));
        userService.deleteUser(1);
    }
}
