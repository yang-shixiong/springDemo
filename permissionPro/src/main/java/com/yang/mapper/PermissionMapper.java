package com.yang.mapper;

import com.yang.domain.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    // 根据role_id获取权限列表
    List<Permission> selectAllByRoleId(Integer id);
}