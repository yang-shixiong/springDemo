package com.yang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yang.domain.*;
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
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 保存员工
            employeeMapper.insert(employee);

            // 保存员工与角色的关系
            for (Role role : employee.getRoles()) {
                employeeMapper.insertRoleRel(employee.getId(), role.getId());
            }
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("插入成功！");
        } catch (Exception e) {
            ajaxRes.setMsg("插入失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*修改员工*/
    @Override
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 删除员工与角色关系
            employeeMapper.deleteRoleRel(employee.getId());
            // 更新员工表
            employeeMapper.updateByPrimaryKey(employee);
            // 保存员工与角色的关系
            for (Role role : employee.getRoles()) {
                employeeMapper.insertRoleRel(employee.getId(), role.getId());
            }
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功！");
        } catch (Exception e) {
            ajaxRes.setMsg("更新失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*修改员工离职状态*/
    @Override
    public AjaxRes changeState(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeMapper.changeState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("离职操作成功！");
        } catch (Exception e) {
            ajaxRes.setMsg("离职操作失败，请重试！");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
