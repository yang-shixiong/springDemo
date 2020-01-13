package controller;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserController {
    UserService userService;

    public void getUser() {
        User user = userService.getUser();
        System.out.println(user);
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
