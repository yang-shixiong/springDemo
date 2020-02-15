package com.yang.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.domain.AjaxRes;
import com.yang.domain.Employee;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.service.EmployeeService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

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
    @RequiresPermissions("employee:update")  // 配置权限
    public AjaxRes employeeUpdate(Employee employee){
        return employeeService.updateEmployee(employee);
    }

    /*离职操作*/
    @RequestMapping("/employee/state")
    @ResponseBody
    public AjaxRes employeeUpdate(Integer id){
        return employeeService.changeState(id);
    }

    /*
        设置异常处理
        参数method就是发生异常的方法
    */
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response)throws Exception{
        /*如果授权异常，则跳转授权页面*/
        // 获取异常方法中的是否是json请求
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if(methodAnnotation != null){
            // 这个就是ajax的请求
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("没有权限操作");
            String res = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(res);
        }else{
            response.sendRedirect("error-permission.jsp");
        }
    }
}
