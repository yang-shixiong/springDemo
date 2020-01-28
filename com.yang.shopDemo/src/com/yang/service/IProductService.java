package com.yang.service;

import com.yang.domain.Product;

import java.util.List;

public interface IProductService {
    // 获取商品
    List<Product> getList();

    // 获取单个商品
    Product getProduct(Integer id);

    // 增加单个商品
    boolean addProduct(Product product);

    // 删除单个商品
    boolean deleteProduct(Integer id);

}
