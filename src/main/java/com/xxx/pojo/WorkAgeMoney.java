package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("WorkAgeMoney")
public class WorkAgeMoney implements Serializable {   // 工龄工资表

    private int workAge; // 工龄年数
    private double baseAgeMoney; // 每年基础加成

    public WorkAgeMoney() {
    }

    public WorkAgeMoney(int workAge, double baseAgeMoney) {
        this.workAge = workAge;
        this.baseAgeMoney = baseAgeMoney;
    }

    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public double getBaseAgeMoney() {
        return baseAgeMoney;
    }

    public void setBaseAgeMoney(double baseAgeMoney) {
        this.baseAgeMoney = baseAgeMoney;
    }

    @Override
    public String toString() {
        return "WorkAgeMoney{" +
                "workAge=" + workAge +
                ", baseAgeMoney=" + baseAgeMoney +
                '}';
    }
}
