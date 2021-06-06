package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Product")
public class Product implements Serializable {

    private String productId; //  产品编号
    private String productName; //  产品名称
    private double productSalePrice; //  产品标准售价
    private String productTypeId; // 产品类别编号
    private String productDescription; // 产品描述

    public Product() {
    }

    public Product(String productId, String productName, double productSalePrice, String productTypeId, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productSalePrice = productSalePrice;
        this.productTypeId = productTypeId;
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(double productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productSalePrice=" + productSalePrice +
                ", productTypeId='" + productTypeId + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
