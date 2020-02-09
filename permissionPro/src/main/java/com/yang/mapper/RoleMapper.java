package com.yang.mapper;

import com.yang.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /*插入角色与权限关系表*/
    void insertRoleAndPermissionRel(@Param("role_id") Integer role_id, @Param("permission_id") Integer permission_id);

    /*根据role_id删除*/
    void deletePermissionRelByRoleId(Integer id);
}