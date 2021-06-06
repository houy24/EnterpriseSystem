package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("ProductWarehouse")
public class ProductWarehouse implements Serializable {  // 产品库存表

    private String productWarehouseId; //  库存编号
    private String productId; //  产品编号
    private String productName; //  产品名称
    private int productNumber; //  产品数量
    private Date InWareHouseTime; //  入厂日期
    private String productionAddress; //  生产地址
    private double oneInPrice; // 单进价
    private double totalInPrice; // 入厂总进价

    public ProductWarehouse() {
    }

    public ProductWarehouse(String productWarehouseId, String productId, String productName, int productNumber, Date inWareHouseTime, String productionAddress, double oneInPrice, double totalInPrice) {
        this.productWarehouseId = productWarehouseId;
        this.productId = productId;
        this.productName = productName;
        this.productNumber = productNumber;
        InWareHouseTime = inWareHouseTime;
        this.productionAddress = productionAddress;
        this.oneInPrice = oneInPrice;
        this.totalInPrice = totalInPrice;
    }

    public String getProductWarehouseId() {
        return productWarehouseId;
    }

    public void setProductWarehouseId(String productWarehouseId) {
        this.productWarehouseId = productWarehouseId;
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

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public Date getInWareHouseTime() {
        return InWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        InWareHouseTime = inWareHouseTime;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public double getOneInPrice() {
        return oneInPrice;
    }

    public void setOneInPrice(double oneInPrice) {
        this.oneInPrice = oneInPrice;
    }

    public double getTotalInPrice() {
        return totalInPrice;
    }

    public void setTotalInPrice(double totalInPrice) {
        this.totalInPrice = totalInPrice;
    }

    @Override
    public String toString() {
        return "ProductWarehouse{" +
                "productWarehouseId='" + productWarehouseId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productNumber=" + productNumber +
                ", InWareHouseTime=" + InWareHouseTime +
                ", productionAddress='" + productionAddress + '\'' +
                ", oneInPrice=" + oneInPrice +
                ", totalInPrice=" + totalInPrice +
                '}';
    }
}
