package com.yang.web;

import com.yang.domain.AjaxRes;
import com.yang.domain.Menu;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    /*注入*/
    @Autowired
    private MenuService menuService;

    /*返回menu*/
    @RequestMapping("/menu")
    public String menu(){
        return "menu";
    }

    /*返回menu列表*/
    @RequestMapping("/menu/list")
    @ResponseBody
    public PageListRes menuList(QueryVo queryVo){
        return menuService.getMenuList(queryVo);
    }

    /*返回所有的菜单*/
    @RequestMapping("/menu/parent/list")
    @ResponseBody
    public List<Menu> parentList(){
        return menuService.getAll();
    }

    /*增加一个菜单*/
    @RequestMapping("/menu/add")
    @ResponseBody
    public AjaxRes menuAdd(Menu menu){
        return menuService.addMenu(menu);
    }

    /*更新菜单*/
    @RequestMapping("/menu/edit")
    @ResponseBody
    public AjaxRes menuUpdate(Menu menu){
        return menuService.updateMenu(menu);
    }

    /*删除菜单*/
    @RequestMapping("/menu/delete")
    @ResponseBody
    public AjaxRes menuUpdate(Integer id){
        return menuService.deleteMenu(id);
    }

    /*获取菜单结构树*/
    @RequestMapping("/menu/tree")
    @ResponseBody
    public List<Menu> menuTree(){
        return menuService.getMenuTree();
    }


}
