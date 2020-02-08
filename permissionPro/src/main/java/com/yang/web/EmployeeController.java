package com.yang.web;

import com.yang.domain.AjaxRes;
import com.yang.domain.Employee;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    /*注入业务层*/
    @Autowired
    private EmployeeService employeeService;

    /*返回employee的jsp文件*/
    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    /*返回员工列表*/
    @RequestMapping("/employee/list")
    @ResponseBody  // 返回json数据
    public PageListRes employeeList(QueryVo queryVo){
        return employeeService.getList(queryVo);
    }

    /*增加员工*/
    @RequestMapping("/employee/add")
    @ResponseBody
    public AjaxRes employeeAdd(Employee employee){
        return employeeService.insertEmployee(employee);
    }

    /*编辑员工*/
    @RequestMapping("/employee/update")
    @ResponseBody
    public AjaxRes employeeUpdate(Employee employee){
        return employeeService.updateEmployee(employee);
    }

    /*离职操作*/
    @RequestMapping("/employee/state")
    @ResponseBody
    public AjaxRes employeeUpdate(Integer id){
        return employeeService.changeState(id);
    }
}
