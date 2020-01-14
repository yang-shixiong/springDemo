package com.yang.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component  // 这个标识符是为了告知spring，这个需要进行加载
@Scope(scopeName = "singleton")  // 这个就是指定作用域，默认是singleton，单例模式
public class Person {
    @Value("1")  // value就是这个属性进行初始化赋值，一般情况下是从配置中读取，现阶段先写死，为了学习
    private int id;
    @Value("female")
    private String Sex;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", Sex='" + Sex + '\'' +
                '}';
    }
}
