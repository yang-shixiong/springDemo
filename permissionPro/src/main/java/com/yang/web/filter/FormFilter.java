package com.yang.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.domain.AjaxRes;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 这个等登陆成功会调用，为了通知web，在这里面重写方法，重写表单监听的过滤器成功以及失败的方法
 */
public class FormFilter extends FormAuthenticationFilter {
    /*当认证成功的时候，会进行调用*/
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(subject);
        // 因为回传信息包含中文，先设置字节码
        response.setCharacterEncoding("utf-8");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("登陆成功！");
        // 把对象转化为json字符串
        String resString = new ObjectMapper().writeValueAsString(ajaxRes);

        // 将字符串写入响应对象
        response.getWriter().print(resString);
        // return false 才会往下走，否则就组织了
        return false;
    }

    /*当认证失败的时候，就会调用,e就是抛出异常*/
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        // 设置响应数据
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(false);
        if (e != null) {
            // 获取异常类的名称
            String name = e.getClass().getName();
            // 没有账号
            if (name.equals(UnknownAccountException.class.getName())) {
                ajaxRes.setMsg("账号不正确");
            } else if (name.equals(IncorrectCredentialsException.class.getName())) {
                ajaxRes.setMsg("密码不正确");  // 密码有误异常
            } else {
                ajaxRes.setMsg("不确认错误");  // 其他错误
            }
        }
        // 序列化返回结果并且放置到响应对象中
        try {
            String resString = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(resString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(ajaxRes.getMsg());
        // return false 才会往下走，否则就阻止了
        // return true;  // 默认是返回true
        return false;
    }
}
