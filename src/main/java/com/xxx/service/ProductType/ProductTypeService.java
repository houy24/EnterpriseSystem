package com.xxx.service.ProductType;

import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getAllProductType();

    ProductType getProductTypeById(String productTypeId);
}
