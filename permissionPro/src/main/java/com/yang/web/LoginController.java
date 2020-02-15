package com.yang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 配置了loginUrl，需要设置对应的函数
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "redirect:login.jsp";
    }
}
