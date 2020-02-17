package com.yang.interceptor;

import com.yang.util.RequestUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request拦截器，继承HandlerInterceptor接口
 */
public class RequestInterceptor implements HandlerInterceptor {
    /*只需要重写前置拦截*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 保存request
        RequestUtil.setRequest(request);
        return true;
    }
}
