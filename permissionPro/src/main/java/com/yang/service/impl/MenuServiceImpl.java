package com.yang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yang.domain.*;
import com.yang.mapper.MenuMapper;
import com.yang.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    /*注入mapper*/
    @Autowired
    private MenuMapper menuMapper;

    /*返回menu列表*/
    @Override
    public PageListRes getMenuList(QueryVo queryVo) {
        // 分页
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        // 查询
        List<Menu> menus = menuMapper.selectAll();
        // 封装返回结果
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(menus);
        return pageListRes;
    }

    /*返回所有的菜单*/
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectAll();
    }

    /*增加一个菜单*/
    @Override
    public AjaxRes addMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        try{
            // 保存菜单
            menuMapper.insert(menu);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("保存菜单成功");
        }catch (Exception e){
            ajaxRes.setMsg("保存菜单失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*更新菜单*/
    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        try{
            // 获取待更新菜单父节点
            if(menu.getParent() != null){
                Integer parentId = menuMapper.selectParentId(menu.getParent().getId());
                if(parentId.equals(menu.getId())){
                    ajaxRes.setSuccess(false);
                    ajaxRes.setMsg("自己的子菜单不能设置为父菜单");
                    return ajaxRes;
                }
            }
            // 更新菜单
            menuMapper.updateByPrimaryKey(menu);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("保存菜单成功");
        }catch (Exception e){
            ajaxRes.setMsg("保存菜单失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*删除菜单*/
    @Override
    public AjaxRes deleteMenu(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try{
            // 保存菜单
            menuMapper.deleteByPrimaryKey(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除菜单成功");
        }catch (Exception e){
            ajaxRes.setMsg("删除菜单失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*获取菜单结构树*/
    @Override
    public List<Menu> getMenuTree() {
        List<Menu> menus = menuMapper.getTreeMenu();
        // 进行权限认证
        // 首先获取当前主体
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        // 判断当前用户是否是管理员，如果是，则跳过验证
        if(!employee.getAdmin()){
            // 进行权限认证
            checkPermission(menus);
        }
        return menus;
    }

    /*进行权限检验*/
    public void checkPermission(List<Menu> menus){

        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 将菜单做成迭代器
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            // 判断当前菜单是否需要进行权限校验
            if(menu.getPermission() != null){
                // 获取权限
                String resource = menu.getPermission().getResource();
                if(!subject.isPermitted(resource)){
                    // 将当前菜单从迭代器中删除，没有权限
                    iterator.remove();
                    continue;
                }
            }
            // 判断是否有子菜单
            if(menu.getChildren().size() >0){
                checkPermission(menu.getChildren());
            }
        }
    }
}
