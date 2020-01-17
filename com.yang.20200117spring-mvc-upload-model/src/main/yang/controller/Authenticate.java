package main.yang.controller;
import main.yang.bean.User;
import main.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Authenticate {

    // 自动加载，这个就是DI注入，应该回忆一下
    @Autowired
    UserService userService;

    @RequestMapping("/authenticate")
    public ModelAndView check (@RequestParam(name = "username", required = true) String username, @RequestParam("password") String password){
        ModelAndView mav = new ModelAndView();
        String msg = "认证失败";
        // 调用server层对象进行处理
        User user = userService.getUser(username);
        if(user.getPassword().equals(password)){
            msg = "认证成功";
        }
        mav.addObject("message", msg);
        mav.setViewName("result");

        return  mav;
    }
}
