package com.yang.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component  // 注意，这个component可以指定名字，就跟bean的ID是一样的，默认是类名首字母小写，不会出现冲突，因为Spring是单例的
public class User {
    @Value("1")
    private int id;
    @Value("ming")
    private String name;

    //    @Autowired  // Autowired就是自动扫描该接口的实现类，如果只有一个成功，如果有多个会报错
//    @Qualifier("person")  // 这个一般搭配Autowired使用，如果一个接口有多个实现类，可以进行指定注入哪个实现类
    @Resource(name = "person") // 这个就是Autowired + Qualifier(name)的组合，如果有多个实现类，可以指定，如果只有一个可以不指定
    private Person person;

    public User() {
    }

    @PostConstruct  // 这个就是初始化调用相当于bean中init-method
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy  // 这个是销毁方法，相当于destroy-method
    public void destroy() {
        System.out.println("销毁");
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", person=" + person +
                '}';
    }
}
