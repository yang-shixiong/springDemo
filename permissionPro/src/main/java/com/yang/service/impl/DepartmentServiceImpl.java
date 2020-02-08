package com.yang.service.impl;

import com.yang.domain.Department;
import com.yang.mapper.DepartmentMapper;
import com.yang.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门业务层
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    // 注入mapper
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getList() {
        return departmentMapper.selectAll();
    }
}
