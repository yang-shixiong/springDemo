package com.yang.dao.Impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component  // 这是数据库配置，因此不属于什么层，使用component
public class DataSources {
    @Value("root")  // 注入值
    private String username;
    @Value("mysql")
    private String password;
    @Value("jdbc:mysql://127.0.0.1:3306/users")
    private String url;
    @Value("com.jdbc.mysql.Driver")
    private String driverClass;
}
