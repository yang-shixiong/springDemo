package com.yang.dao;

import com.yang.domain.Car;

import java.util.List;

public interface ICarDao {
    // 根据用户Id获取购物车内容
    List<Car> getList(Integer userId);

    // 加入购物车
    boolean addCar(Car car);

    // 根据Id从购物车中删除
    boolean deleteCar(Integer id);

    // 根据productId判断是否在购物车中存在
    boolean checkCar(Integer userId, Integer productId);
}
