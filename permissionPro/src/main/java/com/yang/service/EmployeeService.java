package com.yang.service;

import com.yang.domain.AjaxRes;
import com.yang.domain.Employee;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;

import java.util.List;

public interface EmployeeService {
    /*获取员工列表*/
    PageListRes getList(QueryVo queryVo);

    /*z增加员工*/
    AjaxRes insertEmployee(Employee employee);

    /*修改员工*/
    AjaxRes updateEmployee(Employee employee);

    /*修改员工离职状态*/
    AjaxRes changeState(Integer id);
}
