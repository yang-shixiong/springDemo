package com.yang.controller;

import com.yang.domain.Product;
import com.yang.domain.User;
import com.yang.service.IProductService;
import com.yang.service.IUserService;
import com.yang.util.TokenUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/product")
    public String productList(String token, Model model) {
        // 根据token获取用户id
        Integer userId = TokenUse.getUserID(token);
        User user = iUserService.get(userId);
        // 获取商品列表
        List<Product> list = iProductService.getList();
        // 向model中塞模型
        model.addAttribute("productList", list);
        model.addAttribute("user", user);
        return "product";
    }

    @RequestMapping("/detail/{id}/{userId}")
    public String detail(@PathVariable(value = "id") Integer id, @PathVariable(value = "userId") Integer userId, Model model) {
        // 查询商品详情
        Product product = iProductService.getProduct(id);
        // 查询用户
        User user = iUserService.get(userId);
        // 如果没有值，就返回错误页面
        if (product == null | user == null) {
            return "error";
        }
        // 向model中塞模型
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "detail";
    }
}
