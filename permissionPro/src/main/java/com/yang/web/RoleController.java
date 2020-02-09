package com.yang.web;

import com.yang.domain.AjaxRes;
import com.yang.domain.PageListRes;
import com.yang.domain.QueryVo;
import com.yang.domain.Role;
import com.yang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    /*注入业务层*/
    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String role() {
        return "role";
    }

    /*获取角色列表，分页 */
    @RequestMapping("/role/list")
    @ResponseBody
    public PageListRes roleList(QueryVo queryVo) {
        /*调用业务层直接查询*/
        return roleService.getRoles(queryVo);
    }

    /*添加角色*/
    @RequestMapping("/role/add")
    @ResponseBody
    public AjaxRes roleAdd(Role role) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层，保存角色以及权限
            roleService.insertRole(role);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("创建角色成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("创建角色失败，请重试！");
        }
        return ajaxRes;
    }

    /*更新角色*/
    @RequestMapping("/role/update")
    @ResponseBody
    public AjaxRes roleUpdate(Role role) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层，保存角色以及权限
            roleService.updateRole(role);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新角色成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新角色失败，请重试！");
        }
        return ajaxRes;
    }

    /*删除角色*/
    @RequestMapping("/role/delete")
    @ResponseBody
    public AjaxRes roleDelete(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层，删除角色以及权限
            roleService.deleteRole(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除角色成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除角色失败，请重试！");
        }
        return ajaxRes;
    }

    /*获取角色列表 */
    @RequestMapping("/role/list/all")
    @ResponseBody
    public List<Role> roleAllList() {
        /*调用业务层直接查询*/
        return roleService.getAllRoles();
    }
}
