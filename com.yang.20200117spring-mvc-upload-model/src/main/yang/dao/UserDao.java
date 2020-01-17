package main.yang.dao;

import main.yang.bean.User;

// 我们的dao接口
public interface UserDao {

    User getUser(String name);
}
