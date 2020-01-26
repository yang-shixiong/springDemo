package com.yang.service;

import com.yang.domain.User;

public interface IUserService {
    // 保存用户
    boolean save(User user);

    // 删除用户，接受值int
    boolean delete(Integer id);

    // 更新用户
    boolean update(Integer id, User user);

    // 获取用户
    User get(Integer id);

    User get(String username);
}
