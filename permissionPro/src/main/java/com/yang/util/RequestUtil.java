package com.yang.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 读取request的本次线程工具类
 */
public class RequestUtil {
    /*类属性，保证其唯一性*/
    public static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    /*获取request*/
    public static HttpServletRequest getRequest() {
        return local.get();
    }

    /*存储request*/
    public static void setRequest(HttpServletRequest request) {
        local.set(request);
        /**
         * 源码， 我们可以知道这个就是是将该线程作为键，传入的reuqest作为值封装到map中
         * public void set(T value) {
         *         Thread t = Thread.currentThread();
         *         ThreadLocalMap map = getMap(t);
         *         if (map != null) {
         *             map.set(this, value);
         *         } else {
         *             createMap(t, value);
         *         }
         *     }
         */
    }
}
