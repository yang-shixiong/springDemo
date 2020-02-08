package com.yang.web;

import com.yang.domain.Department;
import com.yang.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门控制层
 */
@Controller
public class DepartmentController {

    // 注入业务
    @Autowired
    private DepartmentService departmentService;

    // 查询所有的部门列表
    @RequestMapping("/department/list")
    @ResponseBody
    public List<Department> departmentList(){
        return departmentService.getList();
    }
}
