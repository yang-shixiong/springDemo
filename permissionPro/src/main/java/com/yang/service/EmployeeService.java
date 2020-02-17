package com.yang.service;

import com.yang.domain.AjaxRes;
import com.yang.domain.Employee;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;

import java.util.List;

public interface EmployeeService {
    /*获取员工列表*/
    PageListRes getList(QueryVo queryVo);

    /*增加员工*/
    AjaxRes insertEmployee(Employee employee);

    /*修改员工*/
    AjaxRes updateEmployee(Employee employee);

    /*修改员工离职状态*/
    AjaxRes changeState(Integer id);

    /*根据员工姓名获取员工*/
    Employee getEmployeeByName(String username);

    /*查询该员工所拥有的角色集合*/
    List<String> getRoleByEmployeeId(Integer id);

    /*查询该员工所有的权限集合*/
    List<String> getPermissionByEmployeeId(Integer id);

    /*获取所有的用户*/
    List<Employee> getAll();

    /*通过excel插入*/
    void insertEmployeeInExcel(Employee employee);
}
