package com.yang.service.impl;

import com.yang.dao.ICarDao;
import com.yang.domain.Car;
import com.yang.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    // 生成数据层对象
    @Autowired
    private ICarDao iCarDao;

    // 获取列表
    @Override
    public List<Car> getList(Integer userId) {
        return iCarDao.getList(userId);
    }

    @Override
    public Car getCar(Integer id) {
        return iCarDao.getCar(id);
    }

    @Override
    public boolean addCar(Car car) {

        return iCarDao.addCar(car);
    }

    @Override
    public boolean deleteCar(Integer id) {
        return iCarDao.deleteCar(id);
    }

    @Override
    public boolean checkCar(Integer userId, Integer productId) {
        return iCarDao.checkCar(userId, productId);
    }
}
