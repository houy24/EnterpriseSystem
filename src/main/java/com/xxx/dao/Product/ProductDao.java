package com.xxx.dao.Product;

import com.xxx.pojo.Product;

import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(String productId);

    int insert(Product record);

    Product selectByPrimaryKey(String productId);

    Product selectPyProductName(String productName);

    List<Product> selectAll();

    List<Product> selectByProductTypeId(String productTypeId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}