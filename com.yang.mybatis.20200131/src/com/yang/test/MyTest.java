package com.yang.test;

import com.yang.domain.Order;
import com.yang.domain.User;
import com.yang.mapper.OrderMapper;
import com.yang.mapper.UserMapper;
import com.yang.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void getUser() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取用户映射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 通过定义的接口进行查询数据库
        User user = userMapper.getUserById(1);
        System.out.println(user);  // User{id=1, username='yang', password='1234'}

        List<User> users = userMapper.getUsers();
        for (User user1 : users) {
            System.out.println(user1);
            /*
                User{id=1, username='yang', password='1234'}
                User{id=2, username='shi', password='5678'}
                User{id=3, username='xiong', password='9012'}
             */
        }
        // 增加用户
        User newUser = new User();
        newUser.setUsername("mark");
        newUser.setPassword("1111");
        userMapper.insertUser(newUser);
        System.out.println(newUser);  // User{id=7, username='mark', password='1111'}
        sqlSession.commit();

        // 删除用户
        userMapper.deleteUser(8);
        sqlSession.commit();
        // 关闭连接
        sqlSession.close();
    }

    @Test
    public void getUserMap() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取用户映射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 通过定义的接口进行查询数据库
        Map<String, Object> user = userMapper.getUserMapById(2);
        System.out.println(user);  // {password=5678, id=2, username=shi}
        // 关闭连接
        sqlSession.close();
    }

    @Test
    public void getUseByArgs() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取用户映射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 通过定义的接口进行查询数据库
        User user = userMapper.getUserByArgs(2, "shi");
        System.out.println(user);  // User{id=2, username='shi', password='5678'}
        // 关闭连接
        sqlSession.close();
    }

    @Test
    public void getResultMap() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取用户映射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getResultMapById(2);
        System.out.println(user); // User{id=2, username='shi', password='5678'}
        sqlSession.close();
    }

    @Test
    public void orders() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取用户映射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 获取订单映射
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        User user = userMapper.getUserById(3);
        Order order = new Order();
        order.setName("订单三");
        order.getUsers().add(user);
        // 添加订单
        orderMapper.insertOrder(order);
        // 添加关系映射
        orderMapper.insertRelation(user.getId(), order.getId());
        // 提交事务
        sqlSession.commit();
        // 查询所有的订单
        List<Order> allOrder = orderMapper.getAllOrder();
        for (Order order1 : allOrder) {
            System.out.println(order1);
            /*
                Order{id=3, name='订单三', users=[User{id=2, username='shi', password='5678'}, User{id=6, username='mark', password='1111'}]}
                Order{id=4, name='订单三', users=[User{id=3, username='xiong', password='9012'}, User{id=2, username='shi', password='5678'}]}
                Order{id=5, name='订单三', users=[User{id=3, username='xiong', password='9012'}]}
                Order{id=6, name='订单三', users=[User{id=3, username='xiong', password='9012'}]}
                Order{id=1, name='订单一', users=[]}
                Order{id=2, name='订单二', users=[]}
             */
        }
        sqlSession.close();
    }

    @Test
    public void order() {
        // 建立sql连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 获取订单映射
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 通过分步查询获取订单
        Order order = orderMapper.getOrderById(2);
        System.out.println(order);  // Order{id=3, name='订单三', users=[User{id=2, username='shi', password='5678'}, User{id=6, username='mark', password='1111'}]}
        System.out.println(order);
        sqlSession.close();
    }

}
