package com.yang.test;

import com.yang.domain.Customer;
import com.yang.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestCustomer {


    // 查询单个用户
    @Test
    public void test() {
        // 获取session连接对象（会话）
        SqlSession sqlSession = MyBatisUtil.openSession();
        // 执行sql语句，查询查询一条数据使用selectOne
        Customer customer = sqlSession.selectOne("queryCustomerById", 1);
        System.out.println(customer);  // Customer{id=1, username='yang', job='射手', phone='13499887733', email='12341241@qq.com'}
        // 完毕之后需要关闭会话
        sqlSession.close();
    }

    // 查询所有用户
    @Test
    public void testList() {
        SqlSession sqlSession = MyBatisUtil.openSession();
        List<Customer> customers = sqlSession.selectList("queryAllCustomer");
        for (Customer customer : customers) {
            System.out.println(customer); // Customer{id=1, username='yang', job='射手', phone='13499887733', email='12341241@qq.com'}  ....
        }
        sqlSession.close();
    }

    // 插入操作
    @Test
    public void insert() {
        SqlSession sqlSession = MyBatisUtil.openSession();
        Customer customer = new Customer();
        customer.setUsername("yang2");
        customer.setPhone("18122222222");
        Integer id = sqlSession.insert("insertCustomer", customer);
        // 当改变数据库时，我们需要手动提交
        sqlSession.commit();
        System.out.println(customer);  // Customer{id=12, username='yang', job='null', phone='18122222222', email='null'}
        System.out.println(id);  // 1 这个是影响的行数，想要id直接去返回的结果集中去拿
        sqlSession.close();
    }

    // 更新操作
    @Test
    public void update() {
        SqlSession sqlSession = MyBatisUtil.openSession();
        Customer customer = sqlSession.selectOne("queryCustomerById", 1);
        customer.setUsername("yang");
        System.out.println(customer);  // Customer{id=1, username='yang', job='射手', phone='13499887733', email='12341241@qq.com'}
        sqlSession.update("updateCustomer", customer);
        System.out.println(customer);  // Customer{id=1, username='yang', job='射手', phone='13499887733', email='12341241@qq.com'}
        sqlSession.commit();
        sqlSession.close();
    }

    // 删除
    @Test
    public void delete() {
        SqlSession sqlSession = MyBatisUtil.openSession();
        Customer customer = sqlSession.selectOne("queryCustomerById", 3);
        System.out.println(customer);
        sqlSession.delete("deleteCustomer", customer);
        sqlSession.commit();
        sqlSession.close();
    }
}
