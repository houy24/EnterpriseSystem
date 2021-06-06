package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("SignInRecord")
public class SignInRecord implements Serializable { // 签到记录表

    private String signInId;   // 签到表编号
    private String userId;     // 用户编号
    private String userName;   // 用户姓名
    private Date signInTime;   // 签到时间
    private Date signOutTime;  // 签退时间

    public SignInRecord() {
    }

    public SignInRecord(String signInId, String userId, String userName, Date signInTime, Date signOutTime) {
        this.signInId = signInId;
        this.userId = userId;
        this.userName = userName;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
    }

    public String getSignInId() {
        return signInId;
    }

    public void setSignInId(String signInId) {
        this.signInId = signInId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public Date getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Date signOutTime) {
        this.signOutTime = signOutTime;
    }

    @Override
    public String toString() {
        return "SignInRecord{" +
                "signInId='" + signInId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", signInTime=" + signInTime +
                ", signOutTime=" + signOutTime +
                '}';
    }
}
