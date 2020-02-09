package com.yang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yang.domain.PageListRes;
import com.yang.domain.Permission;
import com.yang.domain.QueryVo;
import com.yang.domain.Role;
import com.yang.mapper.RoleMapper;
import com.yang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    /*注入mapper*/
    @Autowired
    private RoleMapper roleMapper;

    /*获取角色列表, 分页*/
    @Override
    public PageListRes getRoles(QueryVo queryVo) {
        /*调用分页*/
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        /*调用mapper查询*/
        List<Role> roleList = roleMapper.selectAll();
        /*封装成返回结果*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roleList);
        return pageListRes;
    }

    /*增加角色*/
    @Override
    public void insertRole(Role role) {
        // 保存角色
        roleMapper.insert(role);
        // 保存权限
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getId(), permission.getId());
        }
    }

    /*更新角色*/
    @Override
    public void updateRole(Role role) {
        // 删除该角色所有的权限
        roleMapper.deletePermissionRelByRoleId(role.getId());
        // 更新角色
        roleMapper.updateByPrimaryKey(role);
        // 保存现有权限
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getId(), permission.getId());
        }
    }

    /*删除角色以及权限*/
    @Override
    public void deleteRole(Integer id) {

        // 删除权限
        roleMapper.deletePermissionRelByRoleId(id);
        // 删除角色
        roleMapper.deleteByPrimaryKey(id);
    }

    /*获取角色列表*/
    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectAll();
    }
}
