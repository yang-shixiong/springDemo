package main.yang.service.Impl;

import main.yang.bean.User;
import main.yang.dao.UserDao;
import main.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// server接口
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(String name) {
        // 帮助我们调用数据库接口，查询数据，并返回
        return userDao.getUser(name);
    }
}
