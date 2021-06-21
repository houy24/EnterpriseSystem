package com.xxx.service.Product;

import com.xxx.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProductById(String productId);

}
