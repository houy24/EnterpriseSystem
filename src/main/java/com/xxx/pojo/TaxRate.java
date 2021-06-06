package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("TaxRate")
public class TaxRate implements Serializable { // 税率表

    private String taxRateId; // 税率编号  #随机生成UUID
    private double money; // 工资
    private double rate; // 税率
    private double quicklyReduce; // 速算扣除数

    public TaxRate() {
    }

    public TaxRate(String taxRateId, double money, double rate, double quicklyReduce) {
        this.taxRateId = taxRateId;
        this.money = money;
        this.rate = rate;
        this.quicklyReduce = quicklyReduce;
    }

    public String getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(String taxRateId) {
        this.taxRateId = taxRateId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getQuicklyReduce() {
        return quicklyReduce;
    }

    public void setQuicklyReduce(double quicklyReduce) {
        this.quicklyReduce = quicklyReduce;
    }

    @Override
    public String toString() {
        return "TaxRate{" +
                "taxRateId='" + taxRateId + '\'' +
                ", money=" + money +
                ", rate=" + rate +
                ", quicklyReduce=" + quicklyReduce +
                '}';
    }
}

