package com.yang.web;

import com.yang.domain.Permission;
import com.yang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {

    // 注入业务层
    @Autowired
    private PermissionService permissionService;

    // 获取全部权限列表
    @RequestMapping("/permission/list")
    @ResponseBody
    public List<Permission> permissionList(){
        return permissionService.getPermissions();
    }

    // 获取某个角色的所有权限
    @RequestMapping("/permission/show")
    @ResponseBody
    public List<Permission> permissions(Integer id){
        return permissionService.getPermissionsByRoleId(id);
    }
}
