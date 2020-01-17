package main.yang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView login = new ModelAndView("login");
        login.addObject("message", "请登陆！");
        return login;
    }

    // 支持重定向
    @RequestMapping("/redirect")
    public String redirect() {
        // 使用这个以及直接进行页面重定向
        return "redirect: login";
    }
}
