package com.yang.service.impl;

import com.yang.dao.IUserDao;
import com.yang.domain.User;
import com.yang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public boolean save(User user) {
        return iUserDao.save(user);
    }

    @Override
    public boolean delete(Integer id) {
        return iUserDao.delete(id);
    }

    @Override
    public boolean update(Integer id, User user) {
        return iUserDao.update(id, user);
    }

    @Override
    public User get(Integer id) {
        return iUserDao.get(id);
    }

    @Override
    public User get(String username) {
        return iUserDao.get(username);
    }
}
