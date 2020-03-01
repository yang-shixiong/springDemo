package com.yang.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User表
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 设置自增
    private Long id;

    // 映射字段如果跟数据库一致可以不用写
    private String username;

    // name 指定与数据库中字段的映射关系，unique是否唯一，nullable是否可为空， 可以指定长度，string默认字符串长度是255
    // columnDefinition 指定类型 String映射到数据库中字段就是varchar
    @Column(name = "u_sex", unique = false, nullable = true, length = 20, columnDefinition = "text")
    private String sex;

    // 时间精确到天，默认格式是datetime，时间精确到秒，也可以修改为时间戳
    @Temporal(TemporalType.DATE)
    private Date createTime;

    // 这个字段就是不与数据库进行映射
    @Transient
    private String noUse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNoUse() {
        return noUse;
    }

    public void setNoUse(String noUse) {
        this.noUse = noUse;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime=" + createTime +
                ", noUse='" + noUse + '\'' +
                '}';
    }
}
