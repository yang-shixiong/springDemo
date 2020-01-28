package com.yang.dao.impl;

import com.yang.dao.IUserDao;
import com.yang.domain.User;
import com.yang.handler.BeanHandler;
import com.yang.util.CRUDTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements IUserDao {
    // 新建用户
    @Override
    public boolean save(User user) {
        // 创建sql语句
        String sql = "insert into user(username, password) values(?,?)";
        // 使用工具类
        int result = CRUDTemplate.executeUpdate(sql, user.getUsername(), user.getPassword());
        return result == 1;
    }

    // 根据id删除用户
    @Override
    public boolean delete(Integer id) {
        String sql = "delete from user where id = ?";
        int result = CRUDTemplate.executeUpdate(sql, id);
        return result == 1;
    }

    // 根据用户id以及上传的用户信息，进行修改用户信息
    @Override
    public boolean update(Integer id, User user) {
        String sql = "update user set username = ?, password = ?, avatarUrl = ?, sex = ? where id = ?";
        int result = CRUDTemplate.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getAvatarUrl(), user.getSex(), id);
        return result == 1;
    }

    // 根据id查询单个用户信息
    @Override
    public User get(Integer id) {
        String sql = "select * from user where id = ?";
        return CRUDTemplate.executeQuery(sql, new BeanHandler<User>(User.class), id);
    }

    // 根据用户名查询单个用户信息
    @Override
    public User get(String username) {
        String sql = "select * from user where username = ?";
        return CRUDTemplate.executeQuery(sql, new BeanHandler<User>(User.class), username);
    }
}