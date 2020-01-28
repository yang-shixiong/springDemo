package com.yang.service.impl;

import com.yang.dao.IProductDao;
import com.yang.domain.Product;
import com.yang.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> getList() {
        return iProductDao.getList();
    }

    @Override
    public Product getProduct(Integer id) {
        return iProductDao.getProduct(id);
    }

    @Override
    public boolean addProduct(Product product) {
        return iProductDao.addProduct(product);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return iProductDao.deleteProduct(id);
    }
}
