package com.xxx.service.ProductWarehouseService;

import com.alibaba.fastjson.JSONArray;
import com.xxx.pojo.ProductWarehouse;

import java.util.List;

public interface ProductWarehouseService {
    List<ProductWarehouse> getAllProductWarehouse();
    JSONArray toJSONArray(List<ProductWarehouse> list);
    List<ProductWarehouse> getByProductType(String productTypeName);
    List<ProductWarehouse> getByProductName(String productName);
    boolean deleteByProductWarehouseId(String productWarehouseId);
    boolean addOneInventory(ProductWarehouse productWarehouse);
    boolean updateProductNumber(ProductWarehouse productWarehouse);
    ProductWarehouse getByProductWarehouseId(String productWarehouseId);
}
