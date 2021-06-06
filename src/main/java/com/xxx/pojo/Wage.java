package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("Wage")
public class Wage implements Serializable {  // 工资表

    private String wageId; //  工资编号  # 随机生成UUID
    private String userId; //  用户编号
    private String departmentId; //  部门编号
    private Date wageProvideTime; //  工资发放时间
    private double realyWage; //  实发工资

    public Wage() {
    }

    public Wage(String wageId, String userId, String departmentId, Date wageProvideTime, double realyWage) {
        this.wageId = wageId;
        this.userId = userId;
        this.departmentId = departmentId;
        this.wageProvideTime = wageProvideTime;
        this.realyWage = realyWage;
    }

    public String getWageId() {
        return wageId;
    }

    public void setWageId(String wageId) {
        this.wageId = wageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getWageProvideTime() {
        return wageProvideTime;
    }

    public void setWageProvideTime(Date wageProvideTime) {
        this.wageProvideTime = wageProvideTime;
    }

    public double getRealyWage() {
        return realyWage;
    }

    public void setRealyWage(double realyWage) {
        this.realyWage = realyWage;
    }

    @Override
    public String toString() {
        return "Wage{" +
                "wageId='" + wageId + '\'' +
                ", userId='" + userId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", wageProvideTime=" + wageProvideTime +
                ", realyWage=" + realyWage +
                '}';
    }
}
