package main.yang.dao.Impl;

import main.yang.bean.User;
import main.yang.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// dao层对象
@Repository
public class UserDaoImpl implements UserDao {

    // 自动加载我们饿数据库连接
    @Autowired
    DataSource dataSource;

    @Override
    public User getUser(String name) {
        // 模拟查询数据库
        System.out.println("我们查询数据库完成，这个是假的。。。" + dataSource);
        return new User(name, "1234");
    }
}
