package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("UserAccount")
public class UserAccount implements Serializable { // 用户账户表

    private String userId;         // 用户编号id
    private String userPhone;    // 用户手机号
    private String userPassword; // 用户密码
    private String userType;     // 用户类型

    public UserAccount() {
    }

    public UserAccount(String userId, String userPhone, String userPassword, String userType) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userType = userType;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
