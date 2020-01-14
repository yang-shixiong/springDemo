package com.yang.test;

import com.yang.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)   // 这个就是使用spring+Junit框架
@ContextConfiguration("classpath:applicationContext.xml") // 加载spring的配置文件
public class TestUser {
    @Autowired
    UserController uc;

    @Test
    public void test1() {
        uc.getUsers();
    }
}
