package com.yang.web.realm;

import com.yang.domain.Employee;
import com.yang.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置员工权限realm，需要继承authorizingRealm
 */
public class EmployeeRealm extends AuthorizingRealm {

    /*注入*/
    @Autowired
    private EmployeeService employeeService;

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证，来啦");

        // 获取身份信息
        String username = (String) token.getPrincipal();
        System.out.println(username);
        // 根据当前用户名查看当前是否存在当前用户
        Employee employee = employeeService.getEmployeeByName(username);
        // 如果没有查询到employee、，就返回为空
        if (employee == null) {
            return null;
        }
        // 进行认证
        // 认证的参数，主体，正确的密码，盐，还有当前realm的名称
        return new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes("!@#QAZwsx"), this.getName());
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取客户主体
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        // 声明角色集合
        List<String> roles = null;
        // 声明权限集合
        List<String> permissions = null;
        // 判断该角色是否是管理员
        if (employee.getAdmin()) {
            // 是管理员，增加所有权限
            permissions = new ArrayList<>();
            roles = new ArrayList<>();
            // 获取所有权限
            permissions.add("*:*");
        } else {
            // 查询该员工所拥有的角色集合
            roles = employeeService.getRoleByEmployeeId(employee.getId());
            // 查询该员工所有的权限集合
            permissions = employeeService.getPermissionByEmployeeId(employee.getId());
        }
        // 添加授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
}
