package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 一定要继承Controller这个接口，注意包不要导错了
public class Authenticate implements Controller {
    // 这个是我们需要实现的方法，通过名称就可以知道，一个是请求对象，一个是相应对象
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // 因为要进行模型层跟视图层渲染，我们实例化这个对象，并告知我们的视图是那个
        ModelAndView mav = new ModelAndView("views/result.jsp");
        // 在请求中获取参数
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        String message = "认证失败";
        if (userName.equals("ming") && password.equals("1234")) {
            message = "认证成功";
        }
        // 告知要向视图添加对象
        mav.addObject("message", message);
        // 返回我们设置好的mav
        return mav;
    }
}
