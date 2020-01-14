package yang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yang.UserService.UserService;
import yang.bean.User;

public class TestUser {

    @Test
    public void test() {
        // 引入context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.addUser(new User(1, "ming"));
        userService.deleteUser(1);
    }
}
