package com.yang.mapper;

import com.yang.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    /*获取传入节点的父节点*/
    Integer selectParentId(Integer id);

    /*获取菜单结构树*/
    List<Menu> getTreeMenu();
}