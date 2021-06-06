package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("WorkTitle")
public class WorkTitle implements Serializable { // 职称表

    private String workTitleId; // 职称编号
    private String workTitleName; // 职称名称
    private double workTitleMoney; // 职称工资

    public WorkTitle() {
    }

    public WorkTitle(String workTitleId, String workTitleName, double workTitleMoney) {
        this.workTitleId = workTitleId;
        this.workTitleName = workTitleName;
        this.workTitleMoney = workTitleMoney;
    }

    public String getWorkTitleId() {
        return workTitleId;
    }

    public void setWorkTitleId(String workTitleId) {
        this.workTitleId = workTitleId;
    }

    public String getWorkTitleName() {
        return workTitleName;
    }

    public void setWorkTitleName(String workTitleName) {
        this.workTitleName = workTitleName;
    }

    public double getWorkTitleMoney() {
        return workTitleMoney;
    }

    public void setWorkTitleMoney(double workTitleMoney) {
        this.workTitleMoney = workTitleMoney;
    }

    @Override
    public String toString() {
        return "WorkTitle{" +
                "workTitleId='" + workTitleId + '\'' +
                ", workTitleName='" + workTitleName + '\'' +
                ", workTitleMoney=" + workTitleMoney +
                '}';
    }
}
