package com.yang.dao.impl;

import com.yang.dao.IProductDao;
import com.yang.domain.Product;
import com.yang.handler.BeanHandler;
import com.yang.handler.BeanListHandler;
import com.yang.util.CRUDTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
    // 获取商品列表
    @Override
    public List<Product> getList() {
        // 创建sql语句
        String sql = "select * from product";
        //执行
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<Product>(Product.class));
    }

    // 获取单个商品
    @Override
    public Product getProduct(Integer id) {
        // 创建sql语句
        String sql = "select * from product where id = ?";
        // 执行
        return CRUDTemplate.executeQuery(sql, new BeanHandler<Product>(Product.class), id);
    }

    // 增加单个商品
    @Override
    public boolean addProduct(Product product) {
        // 创建插入语句
        String sql = "insert into product(name, price, description, pictureUrl) values(?,?,?,?)";
        // 使用工具类执行插入操作，返回值为int，成功返回1，失败为0
        int result = CRUDTemplate.executeUpdate(sql, product.getName(), product.getPrice(), product.getDescription(), product.getPictureUrl());
        return result == 1;
    }

    // 删除某个商品
    @Override
    public boolean deleteProduct(Integer id) {
        // 创建删除语句
        String sql = "delete from product where id = ?";
        // 根据id，删除商品
        int result = CRUDTemplate.executeUpdate(sql, id);
        return result == 1;
    }
}
