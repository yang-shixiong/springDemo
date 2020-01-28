package com.yang.controller;

import com.yang.domain.Car;
import com.yang.domain.Product;
import com.yang.domain.User;
import com.yang.service.ICarService;
import com.yang.service.IProductService;
import com.yang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    @Autowired
    ICarService iCarService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IProductService iProductService;

    // 添加购物车信息，提前检测，如果有就不允许添加
    @ResponseBody
    @RequestMapping(value = "/car/add")
    public String addCar(Car car) {
        boolean exist = iCarService.checkCar(car.getUserId(), car.getProductId());
        if (exist) {
            return "exist";
        }
        boolean result = iCarService.addCar(car);
        if (result) {
            return "ok";
        }
        return "fail";
    }

    // 根据购物id值进行删除
    @ResponseBody
    @RequestMapping(value = "/car/delete/{id}")
    public String deleteCar(@PathVariable Integer id) {
        boolean result = iCarService.deleteCar(id);
        if (result) {
            return "ok";
        }
        return "fail";
    }

    // 根据userid获取购物车信息
    @RequestMapping("/car/{userId}")
    public String car(@PathVariable(value = "userId") Integer userId, Model model) {
        // 获取购物车列表
        List<Car> carList = iCarService.getList(userId);
        // 获取用户信息
        User user = iUserService.get(userId);
        if (user == null) {
            return "error";
        }

        Map<Integer, Product> productMap = new HashMap<>();
        float totalPrice = 0;
        // 循环购物车，根据里面的商品id去获取商品详情，并保存到字典中
        for (Car car : carList) {
            Product product = iProductService.getProduct(car.getProductId());
            productMap.put(car.getId(), product);
            totalPrice += product.getPrice();
        }
        // 为model添值
        model.addAttribute("carList", carList);
        model.addAttribute("productMap", productMap);
        model.addAttribute("user", user);
        model.addAttribute("totalPrice", totalPrice);
        return "car";
    }
}
