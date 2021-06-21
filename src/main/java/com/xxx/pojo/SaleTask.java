package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("SaleTask")
public class SaleTask implements Serializable {  // 销售任务表

    private String saleTaskId; // 销售任务编号
    private String userId; // 用户编号
    private String productId; // 产品编号'
    private String productWarehouseId;
    private int productPreSumNumber; //  产品初始需卖出数量, # 初始固定
    private int productNumber; // 产品数量
    private double oneInPrice; // 单件进价     # 进价
    private double oneLowestPrice; // 单件最低成交价   # 应该比进价高一些
    private double sumLowestPrice; // 总最低成交价  # 单件最低乘以数量
    private Date taskStartTime; //  开始时间
    private Date latestFinishTime; // 最晚成交时间
    private String finishState; // 完成状态  # 【等待完成，已完成，超时-能完成-扣钱，最低销售额未满足-补差价，拒绝完成|完成不了-无业绩，不可见】

    public SaleTask() {
    }

    public SaleTask(String saleTaskId, String userId, String productId, String productWarehouseId, int productPreSumNumber, int productNumber, double oneInPrice, double oneLowestPrice, double sumLowestPrice, Date taskStartTime, Date latestFinishTime, String finishState) {
        this.saleTaskId = saleTaskId;
        this.userId = userId;
        this.productId = productId;
        this.productWarehouseId = productWarehouseId;
        this.productPreSumNumber = productPreSumNumber;
        this.productNumber = productNumber;
        this.oneInPrice = oneInPrice;
        this.oneLowestPrice = oneLowestPrice;
        this.sumLowestPrice = sumLowestPrice;
        this.taskStartTime = taskStartTime;
        this.latestFinishTime = latestFinishTime;
        this.finishState = finishState;
    }

    public String getSaleTaskId() {
        return saleTaskId;
    }

    public void setSaleTaskId(String saleTaskId) {
        this.saleTaskId = saleTaskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductWarehouseId() {
        return productWarehouseId;
    }

    public void setProductWarehouseId(String productWarehouseId) {
        this.productWarehouseId = productWarehouseId;
    }

    public int getProductPreSumNumber() {
        return productPreSumNumber;
    }

    public void setProductPreSumNumber(int productPreSumNumber) {
        this.productPreSumNumber = productPreSumNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public double getOneInPrice() {
        return oneInPrice;
    }

    public void setOneInPrice(double oneInPrice) {
        this.oneInPrice = oneInPrice;
    }

    public double getOneLowestPrice() {
        return oneLowestPrice;
    }

    public void setOneLowestPrice(double oneLowestPrice) {
        this.oneLowestPrice = oneLowestPrice;
    }

    public double getSumLowestPrice() {
        return sumLowestPrice;
    }

    public void setSumLowestPrice(double sumLowestPrice) {
        this.sumLowestPrice = sumLowestPrice;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getLatestFinishTime() {
        return latestFinishTime;
    }

    public void setLatestFinishTime(Date latestFinishTime) {
        this.latestFinishTime = latestFinishTime;
    }

    public String getFinishState() {
        return finishState;
    }

    public void setFinishState(String finishState) {
        this.finishState = finishState;
    }

    @Override
    public String toString() {
        return "SaleTask{" +
                "saleTaskId='" + saleTaskId + '\'' +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", productPreSumNumber=" + productPreSumNumber +
                ", productNumber=" + productNumber +
                ", oneInPrice=" + oneInPrice +
                ", oneLowestPrice=" + oneLowestPrice +
                ", sumLowestPrice=" + sumLowestPrice +
                ", taskStartTime=" + taskStartTime +
                ", latestFinishTime=" + latestFinishTime +
                ", finishState='" + finishState + '\'' +
                '}';
    }
}
