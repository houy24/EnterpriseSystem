package com.xxx.dao.ProductType;

import com.xxx.pojo.ProductType;

import java.util.List;

public interface ProductTypeDao {
    int deleteByPrimaryKey(String productTypeId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(String productTypeId);

    List<ProductType> selectAll();

    //新增，通过类型名称获取
    ProductType selectByProductTypeName(String productTypeName);

    ProductType getByProductTypeName(String productTypeName);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
}