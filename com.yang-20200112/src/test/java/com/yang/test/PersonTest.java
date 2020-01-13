package com.yang.test;

import com.yang.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    /**
     * 这个是传统方法进行的测试
     */
    @Test
    public void test1(){
        Person p1 = new Person(1, "ming", "female");
        System.out.println(p1);
    }

    /**
     * 这个是基于spring进行的测试，我们可以发现，对象不再由我们进行创建，而是交给spring，
     * 这样做的好处是，项目编译完成，我们还可以调整配置文件，改变输出的值，这样不就实现了解耦
     */
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) ac.getBean("personId");
        System.out.println(person);
    }
}
