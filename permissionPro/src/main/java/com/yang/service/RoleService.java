package com.yang.service;

import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.domain.Role;

import java.util.List;

/**
 * role的service层
 */
public interface RoleService {
    /*获取角色列表, 分页*/
    PageListRes getRoles(QueryVo queryVo);

    /*增加角色*/
    void insertRole(Role role);

    /*更新角色*/
    void updateRole(Role role);

    /*删除角色以及权限*/
    void deleteRole(Integer id);

    /*获取角色列表*/
    List<Role> getAllRoles();
}
