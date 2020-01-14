package com.yang.Transaction;

// 这个就是我们的事务类
public class Transaction {

    // 定义在方法之前调用的，也就是AOP中的前置通知
    public void before() {
        System.out.println("-----will do something before you---");
    }

    // 定义一个在方法之后调用的增强功能，也就是AOP中后置通知
    public void after() {
        System.out.println("-----will do something after you-----");
    }
}
