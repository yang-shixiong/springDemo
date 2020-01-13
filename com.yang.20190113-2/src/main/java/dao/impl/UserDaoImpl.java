package dao.impl;

import bean.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {
    DataSources ds;

    public UserDaoImpl() {
    }

    public UserDaoImpl(DataSources ds) {
        this.ds = ds;
    }

    public User getUser() {
        System.out.println("模拟数据库连接" + ds);
        return new User(1, "mark", "1234");
    }
}
