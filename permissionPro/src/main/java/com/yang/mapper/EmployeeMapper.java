package com.yang.mapper;

import com.yang.domain.Employee;
import com.yang.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll(QueryVo queryVo);

    int updateByPrimaryKey(Employee record);

    int changeState(Integer id);

    // 保存员工与角色的关系
    void insertRoleRel(@Param("employee_id") Integer employee_id, @Param("role_id") Integer role_id);

    /*删除员工与角色关系*/
    void deleteRoleRel(Integer id);
}