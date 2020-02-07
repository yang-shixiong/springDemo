package com.yang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

    /*注入业务层*/

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }
}
