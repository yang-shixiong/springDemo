package com.yang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

// 为了方便测试，我们封装一个获取连接对象的工具类
public class MyBatisUtil {

    // 声明session工厂
    public static final SqlSessionFactory sessionFactory;

    static {
        // 加载配置文件
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = null;
        try {
            // 读取配置文件
            inputStream = Resources.getResourceAsStream("SqlMappingConfig.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取session工厂
        sessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    // 获取连接对象
    public static SqlSession openSession() {
        return sessionFactory.openSession();
    }
}
