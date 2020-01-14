package com.yang.UserService;

import com.yang.bean.User;

// 定义接口
public interface UserService {
    void addUser(User user);

    void deleteUser(int userID);
}
