package com.yang.mapper;

import com.yang.domain.Employee;
import com.yang.domain.QueryVo;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll(QueryVo queryVo);

    int updateByPrimaryKey(Employee record);

    int changeState(Integer id);
}