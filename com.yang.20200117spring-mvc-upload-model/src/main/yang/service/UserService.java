package main.yang.service;

import main.yang.bean.User;

// 我们饿server接口
public interface UserService {

    User getUser(String name);
}
