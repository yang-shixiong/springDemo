package com.yang.Transaction;

import com.yang.UserService.UserService;
import com.yang.bean.User;

// 这个旧市我们的代理类，可以发现他跟原始对象都是继承了UserService接口
public class ProxyUser implements UserService {

    private UserService us;
    private Transaction ts;

    // 初始化，需要把UserService以及事务类全部传进来
    public ProxyUser(UserService us, Transaction ts) {
        this.us = us;
        this.ts = ts;
    }

    // 重写方法，可以看出在原始对象的方法上下分别添加了我们的增强方法
    public void addUser(User user) {
        ts.before();
        us.addUser(user);
        ts.after();
    }

    public void deleteUser(int userID) {
        ts.before();
        us.deleteUser(userID);
        ts.after();
    }
}
