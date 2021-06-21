package com.xxx.service.ProductType;

import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.pojo.ProductType;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDao productTypeDao = null;

    public ProductTypeServiceImpl() {
        productTypeDao = new ProductTypeDaoImpl();
    }


    @Override
    public List<ProductType> getAllProductType() {
        return productTypeDao.selectAll();
    }

    @Override
    public ProductType getProductTypeById(String productTypeId) {
        return productTypeDao.selectByPrimaryKey(productTypeId);
    }
}
