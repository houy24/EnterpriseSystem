package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("UserData")
public class UserData implements Serializable { // 用户信息表

    private String userId;      // 用户编号
    private String userPhone;   // 用户手机号
    private String userName;    // 用户姓名
    private String userSex;     // 用户性别
    private String userEmail;   // 电子邮箱
    private int userAge;        // 用户年龄
    private String userType;    // 用户类型
    private String departmentId; // 部门编号
    private String positionId;  // 职位编号
    private String workTitleId; //  职称编号
    private int workAge;        //  工龄
    private String userPhoto;   // 个人照片

    public UserData() {
    }

    public UserData(String userId, String userPhone, String userName, String userSex, String userEmail, int userAge, String userType, String departmentId, String positionId, String workTitleId, int workAge, String userPhoto) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userSex = userSex;
        this.userEmail = userEmail;
        this.userAge = userAge;
        this.userType = userType;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.workTitleId = workTitleId;
        this.workAge = workAge;
        this.userPhoto = userPhoto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getWorkTitleId() {
        return workTitleId;
    }

    public void setWorkTitleId(String workTitleId) {
        this.workTitleId = workTitleId;
    }

    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAge=" + userAge +
                ", userType='" + userType + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", positionId='" + positionId + '\'' +
                ", workTitleId='" + workTitleId + '\'' +
                ", workAge=" + workAge +
                ", userPhoto='" + userPhoto + '\'' +
                '}';
    }

}
