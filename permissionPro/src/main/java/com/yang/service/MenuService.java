package com.yang.service;

import com.yang.domain.AjaxRes;
import com.yang.domain.Menu;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;

import java.util.List;

public interface MenuService {
    /*返回menu列表*/
    PageListRes getMenuList(QueryVo queryVo);

    /*返回所有的菜单*/
    List<Menu> getAll();

    /*增加一个菜单*/
    AjaxRes addMenu(Menu menu);

    /*更新菜单*/
    AjaxRes updateMenu(Menu menu);

    /*删除菜单*/
    AjaxRes deleteMenu(Integer id);

    /*获取菜单结构树*/
    List<Menu> getMenuTree();
}
