package com.yang;

public class Person {
    private int id;
    private String Name;
    private String sex;

    public Person() {

    }

    public Person(int id, String name, String sex) {
        this.id = id;
        Name = name;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return String.format("Person id: %s, name:%s, sex: %s", this.id, this.Name, this.sex);
    }

}
