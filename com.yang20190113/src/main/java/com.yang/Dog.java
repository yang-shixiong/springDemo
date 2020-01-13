package com.yang;

import java.util.Arrays;

public class Dog {
    private int id;
    private String name;
    private String masterName;
    private String[] loveFood;

    public void createDog() {
        System.out.println("Dog实例要被创建啦");
    }

    public void destroyDog() {
        System.out.println("Dog实例要被销毁啦");

    }

    public Dog() {
        System.out.println("Dog 无参构造函数");

    }

    public Dog(int id, String name, String masterName, String[] loveFood) {
        this.loveFood = loveFood;
        System.out.println("Dog 有参数构造函数");
        this.id = id;
        this.name = name;
        this.masterName = masterName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String[] getLoveFood() {
        return loveFood;
    }

    public void setLoveFood(String[] loveFood) {
        this.loveFood = loveFood;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", masterName='" + masterName + '\'' +
                ", loveFood=" + Arrays.toString(loveFood) +
                '}';
    }
}
