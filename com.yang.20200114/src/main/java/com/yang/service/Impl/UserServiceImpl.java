package com.yang.service.Impl;

import com.yang.bean.User;
import com.yang.dao.UserDao;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
