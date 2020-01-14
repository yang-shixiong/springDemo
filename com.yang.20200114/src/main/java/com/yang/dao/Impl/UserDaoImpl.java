package com.yang.dao.Impl;

import com.yang.bean.User;
import com.yang.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository  // Dao层，功能与component一样
public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSources dataSources;

    public List<User> getUsers() {
        System.out.println("模拟查询数据库" + dataSources);
        User user1 = new User(1, "ning", "male");
        User user2 = new User(2, "ming", "male");
        User user3 = new User(3, "hong", "female");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
