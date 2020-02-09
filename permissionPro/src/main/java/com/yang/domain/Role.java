package com.yang.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;

    private String number;

    private String name;

    /*一个角色对应多个权限*/
    private List<Permission> permissions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}