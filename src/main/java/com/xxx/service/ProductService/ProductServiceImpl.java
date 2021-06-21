package com.xxx.service.ProductService;

import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.pojo.Product;

public class ProductServiceImpl implements ProductService{
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public boolean addOneProduct(Product product) {
        int i = productDao.insert(product);
        if (i > 0)
            return true;
        return false;
    }
}
