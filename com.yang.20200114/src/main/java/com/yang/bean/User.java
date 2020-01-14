package com.yang.bean;

import lombok.Data;

@Data  // 可以帮助我们创建无参构造函数
public class User {
    private int id;
    private String name;
    private String sex;

    public User(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
