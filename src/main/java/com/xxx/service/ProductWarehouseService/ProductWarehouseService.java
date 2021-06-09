package com.xxx.service.ProductWarehouseService;

import com.alibaba.fastjson.JSONArray;
import com.xxx.pojo.ProductWarehouse;

import java.util.List;

public interface ProductWarehouseService {
    List<ProductWarehouse> getAllProductWarehouse();
    JSONArray toJSONArray(List<ProductWarehouse> list);
}
