package com.yang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    // session工厂
    public static final SqlSessionFactory sessionFactory;

    static {
        // 创建工厂类，并读取配置文件
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder() ;
        InputStream inputStream = null;
        try{
            inputStream = Resources.getResourceAsStream("SqlMappingConfig.xml");
        }catch (IOException e){
            e.printStackTrace();
        }
        // 获取session工厂
        sessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    public static SqlSession openSession(){
        return sessionFactory.openSession();
    }
}
