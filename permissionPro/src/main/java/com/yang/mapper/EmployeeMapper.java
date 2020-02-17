package com.yang.mapper;

import com.yang.domain.Employee;
import com.yang.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int changeState(Integer id);

    // 保存员工与角色的关系
    void insertRoleRel(@Param("employee_id") Integer employee_id, @Param("role_id") Integer role_id);

    /*删除员工与角色关系*/
    void deleteRoleRel(Integer id);

    /*根据员工姓名获取员工*/
    Employee getEmployeeByName(String username);

    /*查询该员工所拥有的角色集合*/
    List<String> getRoleByEmployeeId(Integer id);

    /*查询该员工所有的权限集合*/
    List<String> getPermissionByEmployeeId(Integer id);

    /*通过excel插入*/
    void insertInExcel(Employee employee);
}