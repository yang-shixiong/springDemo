package service.impl;

import bean.User;
import dao.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao ud) {
        this.userDao = ud;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public User getUser() {
        return userDao.getUser();
    }
}
