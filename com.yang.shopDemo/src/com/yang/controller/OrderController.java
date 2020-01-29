package com.yang.controller;

import com.yang.domain.*;
import com.yang.service.ICarService;
import com.yang.service.IOrderService;
import com.yang.service.IProductService;
import com.yang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    IOrderService iOrderService;
    @Autowired
    ICarService iCarService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IProductService iProductService;

    // 创建订单并跳转到结算页面
    @ResponseBody
    @RequestMapping("/order/add/{userId}/{carId}")
    public int AddOrder(@PathVariable("userId") Integer userId, @PathVariable("carId") Integer carId) {
        List<Car> carList = new ArrayList<>();
        float totalPrice = 0;
        // 检查是单独结算，还是整体结算
        if (carId == 0) {
            carList = iCarService.getList(userId);
            for (Car car : carList) {
                totalPrice += car.getPrice();
            }
        } else {
            Car car = iCarService.getCar(carId);
            carList.add(car);
            totalPrice = car.getPrice();
        }
        // 生成订单模型
        Order order = new Order();
        order.setPrice(totalPrice);
        order.setUserId(userId);
        // 返回生成 的订单id
        return iOrderService.addOrder(order, carList);
    }

    // 跳转到订单页面
    @RequestMapping("/order/{orderId}/{userId}")
    public String Order(@PathVariable("orderId") Integer orderId, @PathVariable("userId") Integer userId, Model model) {

        User user = iUserService.get(userId);
        // 查询订单
        Order order = iOrderService.getOrder(userId, orderId);
        if (order == null) {
            model.addAttribute("message", "访问的订单不存在");
            return "error";
        }
        // 查询订单下每一行的信息
        List<OrderDetail> orderDetails = iOrderService.getDetail(orderId);
        Map<Integer, Product> productMap = new HashMap<>();
        // 获取订单中商品信息，并封装
        for (OrderDetail orderDetail : orderDetails) {
            Product product = iProductService.getProduct(orderDetail.getProductId());
            productMap.put(orderDetail.getId(), product);
        }
        // 向模型塞数据
        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("productMap", productMap);
        return "orders";
    }

    @ResponseBody
    @RequestMapping("/order/update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId) {
        // 这里新建一个对象的原因是为了以后可扩展，如果修改订单其他数据直接修改controller层数据就好
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("已付款");
        boolean result = iOrderService.updateOrder(orderId, order);
        if (result) {
            return "ok";
        }
        return "fail";
    }

    // 展示当前用户所有的订单
    @RequestMapping("/order/list/{userId}")
    public String orderList(@PathVariable Integer userId, Model model) {
        User user = iUserService.get(userId);
        List<Order> orderList = iOrderService.getList(userId);
        // 封装一下订单详情，键就是订单的id
        Map<Integer, List<OrderDetail>> orderMap = new HashMap<>();
        // 捕捉所有的商品
        Map<Integer, Product> productMap = new HashMap<>();
        for (Order order : orderList) {
            List<OrderDetail> orderDetails = iOrderService.getDetail(order.getId());
            orderMap.put(order.getId(), orderDetails);
            for (OrderDetail orderDetail : orderDetails) {
                Product product = iProductService.getProduct(orderDetail.getProductId());
                productMap.put(product.getId(), product);
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("orderMap", orderMap);
        model.addAttribute("productMap", productMap);
        model.addAttribute("orderList", orderList);
        return "myOrders";
    }


}
