package com.xxx.dao.ProductWarehouse;

import com.xxx.pojo.ProductWarehouse;

import java.util.List;

public interface ProductWarehouseDao {
    int deleteByPrimaryKey(String productWarehouseId);

    int insert(ProductWarehouse record);

    int insertSelective(ProductWarehouse record);

    ProductWarehouse selectByPrimaryKey(String productWarehouseId);

    List<ProductWarehouse> selectAll();

    List<ProductWarehouse> selectAllByProductId(String productId);

    List<ProductWarehouse> selectAllByProductName(String productName);

    int updateByPrimaryKeySelective(ProductWarehouse record);

    int updateByPrimaryKey(ProductWarehouse record);
}