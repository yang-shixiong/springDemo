package yang.UserService.Impl;

import yang.UserService.UserService;
import yang.bean.User;

// 基本业务层代码，没啥好说的
public class UserServiceImpl implements UserService {
    public void addUser(User user) {
        // 抛出异常
        System.out.println(1 / 0);
        System.out.println("will add a user" + user);
    }

    public void deleteUser(int userID) {
        System.out.println("will delete user:" + userID);
    }
}
