package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Position")
public class Position implements Serializable {  // 职位表

    private String positionId; // 职位编号
    private String positionName; // 职位名称
    private double positionMoney; // 岗位工资
    private String positionDesrciption; // 职位描述

    public Position() {
    }

    public Position(String positionId, String positionName, double positionMoney, String positionDesrciption) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.positionMoney = positionMoney;
        this.positionDesrciption = positionDesrciption;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public double getPositionMoney() {
        return positionMoney;
    }

    public void setPositionMoney(double positionMoney) {
        this.positionMoney = positionMoney;
    }

    public String getPositionDesrciption() {
        return positionDesrciption;
    }

    public void setPositionDesrciption(String positionDesrciption) {
        this.positionDesrciption = positionDesrciption;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionMoney=" + positionMoney +
                ", positionDesrciption='" + positionDesrciption + '\'' +
                '}';
    }
}
