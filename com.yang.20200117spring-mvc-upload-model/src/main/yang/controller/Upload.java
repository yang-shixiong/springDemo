package main.yang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Upload {

    // 文件上传，需要带入common-io以及common-filepath两个包，并在dispatcherServlet中配置
    @RequestMapping("/upload")
    public ModelAndView upload(MultipartFile file) {
        ModelAndView mav = new ModelAndView("show");
        String msg = "上传失败";
        if (!file.isEmpty()) {
            msg = "上传成功";
        }
        mav.addObject("message", msg);
        return mav;
    }
}
