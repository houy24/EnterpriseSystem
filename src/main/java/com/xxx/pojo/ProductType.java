package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("ProductType")
public class ProductType implements Serializable { // 产品类别表

    private String productTypeId; // 产品类别编号
    private String productTypeName; // 产品类型
    private double productCommission; // 产品提成

    public ProductType() {
    }

    public ProductType(String productTypeId, String productTypeName, double productCommission) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.productCommission = productCommission;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public double getProductCommission() {
        return productCommission;
    }

    public void setProductCommission(double productCommission) {
        this.productCommission = productCommission;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "productTypeId='" + productTypeId + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", productCommission=" + productCommission +
                '}';
    }
}
