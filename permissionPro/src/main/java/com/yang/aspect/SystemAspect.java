package com.yang.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.domain.Systemlog;
import com.yang.mapper.SystemlogMapper;
import com.yang.util.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 配置写入日志的类
 */
public class SystemAspect {

    /*注入mapper*/
    @Autowired
    private SystemlogMapper systemlogMapper;

    /*处理写入日志的方法，切片会把切入点传进来，我们可以接受*/
    public void writeLog(JoinPoint point) throws JsonProcessingException {
        Systemlog systemlog = new Systemlog();
        // 设置日志
        systemlog.setOptime(new Date());
        // 设置request
        HttpServletRequest request = RequestUtil.getRequest();
        // 检测request是否为空，进行第一次授权的时候会先到realm中，不会先到拦截器中
        if (request != null) {
            // 设置ip
            systemlog.setIp(request.getRemoteAddr());
        }
        // 在切入点中获取类名
        String name = point.getTarget().getClass().getName();
        // 从切入点中获取签名
        String signature = point.getSignature().getName();
        // 设置函数名
        systemlog.setFunction(name + ":" + signature);
        // 获取参数，并序列化为字符串
        String params = new ObjectMapper().writeValueAsString(point.getArgs());
        // 设置参数
        systemlog.setParams(params);
        systemlogMapper.insert(systemlog);
    }
}
