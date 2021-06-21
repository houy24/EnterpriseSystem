package com.xxx.service.Product;

import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.pojo.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = null;

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.selectAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productDao.selectByPrimaryKey(productId);
    }
}
