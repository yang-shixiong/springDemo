package com.yang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {

    // 设置数据库操作会话工厂
    public static final SqlSessionFactory sessionFactory;

    static {
        // 加载配置文件
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("SqlMappingConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 这个就是加载指定环境变量的environment
        // sessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream, "prod");
        // 如果不加，就是使用配置文件中default的值
        sessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
    }

    // 获取会话连接对象
    public static SqlSession openSession(){
        return sessionFactory.openSession();
    }
}
