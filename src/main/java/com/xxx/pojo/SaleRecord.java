package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("SaleRecord")
public class SaleRecord implements Serializable { // 销售记录表

    private String saleRecordId; // 销售记录编号, # 随机生成UUID
    private String userId; // 用户编号
    private String saleTaskId; // '销售任务编号
    private String productId; // 产品编号
    private int saleNumber; // 销售数量
    private double saleOneMoney; // 销售单价成交价  # 单件成交价
    private double saleSumMoney; // 销售总金额   # 单件成交价 * 销售数量
    private Date saleFinishTime; // 销售时间
    private String saleRecordState; // 销售记录状态

    public SaleRecord() {
    }

    public SaleRecord(String saleRecordId, String userId, String saleTaskId, String productId, int saleNumber, double saleOneMoney, double saleSumMoney, Date saleFinishTime, String saleRecordState) {
        this.saleRecordId = saleRecordId;
        this.userId = userId;
        this.saleTaskId = saleTaskId;
        this.productId = productId;
        this.saleNumber = saleNumber;
        this.saleOneMoney = saleOneMoney;
        this.saleSumMoney = saleSumMoney;
        this.saleFinishTime = saleFinishTime;
        this.saleRecordState = saleRecordState;
    }

    public String getSaleRecordId() {
        return saleRecordId;
    }

    public void setSaleRecordId(String saleRecordId) {
        this.saleRecordId = saleRecordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSaleTaskId() {
        return saleTaskId;
    }

    public void setSaleTaskId(String saleTaskId) {
        this.saleTaskId = saleTaskId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public double getSaleOneMoney() {
        return saleOneMoney;
    }

    public void setSaleOneMoney(double saleOneMoney) {
        this.saleOneMoney = saleOneMoney;
    }

    public double getSaleSumMoney() {
        return saleSumMoney;
    }

    public void setSaleSumMoney(double saleSumMoney) {
        this.saleSumMoney = saleSumMoney;
    }

    public Date getSaleFinishTime() {
        return saleFinishTime;
    }

    public void setSaleFinishTime(Date saleFinishTime) {
        this.saleFinishTime = saleFinishTime;
    }

    public String getSaleRecordState() {
        return saleRecordState;
    }

    public void setSaleRecordState(String saleRecordState) {
        this.saleRecordState = saleRecordState;
    }

    @Override
    public String toString() {
        return "SaleRecord{" +
                "saleRecordId='" + saleRecordId + '\'' +
                ", userId='" + userId + '\'' +
                ", saleTaskId='" + saleTaskId + '\'' +
                ", productId='" + productId + '\'' +
                ", saleNumber=" + saleNumber +
                ", saleOneMoney=" + saleOneMoney +
                ", saleSumMoney=" + saleSumMoney +
                ", saleFinishTime=" + saleFinishTime +
                ", saleRecordState='" + saleRecordState + '\'' +
                '}';
    }
}
