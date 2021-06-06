package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Department")
public class Department implements Serializable {  // 部门表

    private String departmentId; // 部门编号
    private String departmentName; // 部门名称
    private String departmentDesrciption; // 部门描述

    public Department() {
    }

    public Department(String departmentId, String departmentName, String departmentDesrciption) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDesrciption = departmentDesrciption;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDesrciption() {
        return departmentDesrciption;
    }

    public void setDepartmentDesrciption(String departmentDesrciption) {
        this.departmentDesrciption = departmentDesrciption;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentDesrciption='" + departmentDesrciption + '\'' +
                '}';
    }
}
