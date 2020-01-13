package com.yang.test;

import com.yang.Dog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DogTest {

    @Test
    public void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Dog d1 = context.getBean("d1", Dog.class);
        System.out.println(d1);
        Dog d1Name = context.getBean("dog1", Dog.class);
        System.out.println(d1Name);
        Dog d2 = context.getBean("d2", Dog.class);
        System.out.println(d2);
        Dog d3 = context.getBean("d3", Dog.class);
        System.out.println(d3);
        Dog d4 = context.getBean("d4", Dog.class);
        System.out.println(d4);
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        context.close();
    }
}
