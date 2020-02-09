package com.yang.service;

import com.yang.domain.Permission;

import java.util.List;

/**
 * 权限业务接口
 */
public interface PermissionService {
    /*权限服务接口*/
    List<Permission> getPermissions();

    /*根据角色id获取所有权限*/
    List<Permission> getPermissionsByRoleId(Integer id);
}
