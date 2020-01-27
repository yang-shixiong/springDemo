package com.yang.interceptor;

import com.yang.util.TokenUse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //当处理器方法执行之前调用
    //返回值: true  放行   false  不放行    就执行不了处理器方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle当处理器方法执行之前调用");
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if(token != null){
            // 判断是否携带token，并进行验证
            return TokenUse.tokenVerify(token);
        }
        return false;
    }

    @Override
    //当处理器方法执行之后会自动调用调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle当处理器方法执行之后会自动调用调用");
    }

    @Override
    //请求处理完毕之后, 会调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion请求处理完毕之后,会调用");
    }
}
