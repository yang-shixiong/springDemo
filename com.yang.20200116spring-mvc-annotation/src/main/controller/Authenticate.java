package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller  // Controller就是通知Spring框架进行扫描的，我这个试了一下，用Component就无法访问，具体原理后续再研究
//@RequestMapping("/index")  // 在类前面加这个注解，就会给下面所有方法前面都加上这个路由
public class Authenticate {
    // 这个就是路由
    @RequestMapping("/authenticate")
    // @RequestParam 就是我们说到的可以给参数起别名，这样如果前段字段名称发生变化，就只需要修改一处,可以指定
    public ModelAndView handleRequest(@RequestParam(value = "username", required = true, defaultValue = "hong") String u, @RequestParam("password") String p) throws Exception {
        // 看这个，是不是就不再需要我们写前缀跟后缀了，这个即使在despatcher-servlet中配置的
        ModelAndView mav = new ModelAndView("result");
        String message = "认证失败";
        if (u.equals("ming") && p.equals("1234")) {
            message = "认证成功";
        }
        // 告知要向视图添加对象
        mav.addObject("message", message);
        // 返回我们设置好的mav
        return mav;
    }
}
