package com.yang.controller;

import com.yang.domain.User;
import com.yang.service.IUserService;
import com.yang.util.TokenUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    // 返回登陆页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // 返回注册页面
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    // 用户登陆认证
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        User user = iUserService.get(username);
        if (user != null && user.getPassword().equals(password)) {
            // 创建token
            String token = TokenUse.sign(username, user.getId());
            if (token != null) {
                return token;
            }
        }
        return "fail";
    }

    // 用户登陆认证
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User u) {
        User user = iUserService.get(u.getUsername());
        if (user != null) {
            return "exit";
        }
        boolean result = iUserService.save(u);
        if (result) {
            return "ok";
        } else {
            return "fail";
        }
    }

    // 获取用户信息
    @ResponseBody
    @RequestMapping(value = "/user/info", method = RequestMethod.POST)
    public User register(String token) {
        // 通过token获取用户ID
        Integer userId = TokenUse.getUserID(token);
        return iUserService.get(userId);
    }


}
