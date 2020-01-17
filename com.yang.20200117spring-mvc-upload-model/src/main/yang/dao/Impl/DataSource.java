package main.yang.dao.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 这个就是数据库啦
@Component
public class DataSource {
    @Value("root")
    private String username;
    @Value("mysql")
    private String password;
    @Value("http://127.0.0.1:3306/....")
    private String url;
    @Value("mysql.driver....")
    private String classDriver;
}
