package com.yang.service.impl;

import com.yang.domain.Permission;
import com.yang.mapper.PermissionMapper;
import com.yang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限业务层实现类
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    // 注入mapper
    @Autowired
    private PermissionMapper permissionMapper;


    // 获取权限列表
    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.selectAll();
    }

    // 根据role_id获取权限列表
    @Override
    public List<Permission> getPermissionsByRoleId(Integer id) {
        return permissionMapper.selectAllByRoleId(id);
    }
}
