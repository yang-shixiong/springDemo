package com.yang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yang.domain.AjaxRes;
import com.yang.domain.Employee;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.mapper.EmployeeMapper;
import com.yang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  // 开启事务，保证事务提交
public class EmployeeServiceImpl implements EmployeeService {

    /*注入员工查询数据库*/
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageListRes getList(QueryVo queryVo) {
        // 使用分页器
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        // 调用mapper查询员工
        List<Employee> employees = employeeMapper.selectAll(queryVo);
        // 封装返回结果
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    /*增加员工*/
    @Override
    public AjaxRes insertEmployee(Employee employee) {
        employee.setState(true);
        int result = employeeMapper.insert(employee);
        AjaxRes ajaxRes = new AjaxRes();
        if(result == 1){
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("插入成功！");
        }else{
            ajaxRes.setMsg("插入失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*修改员工*/
    @Override
    public AjaxRes updateEmployee(Employee employee) {
        int result = employeeMapper.updateByPrimaryKey(employee);
        AjaxRes ajaxRes = new AjaxRes();
        if(result == 1){
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功！");
        }else{
            ajaxRes.setMsg("更新失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*修改员工离职状态*/
    @Override
    public AjaxRes changeState(Integer id) {
        int result = employeeMapper.changeState(id);
        AjaxRes ajaxRes = new AjaxRes();
        if(result == 1){
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("离职操作成功！");
        }else{
            ajaxRes.setMsg("离职操作失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
