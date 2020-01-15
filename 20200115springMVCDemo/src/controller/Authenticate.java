package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticate implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("views/result.jsp");
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        String message = "认证失败";
        if(userName.equals("ming") && password.equals("1234")){
            message = "认证成功";
        }
        mav.addObject("message", message);
        return mav;
    }
}
