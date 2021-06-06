package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("PersonalResume")
public class PersonalResume implements Serializable { // 个人简历表

    private String PersonalResumeId;  // 个人简历表编号
    private String userId ;          // 用户编号
    private String userName;         // 用户姓名
    private String userSex;          // 用户性别
    private int userAge;         // 用户年龄
    private String userPhone;       // 用户手机号
    private String userEmail;       // 电子邮箱
    private String educationExperience; // 教育经历
    private String jobTarget;       // 求职意向
    private String workExperience;  // 工作经历
    private String awards;      // 获奖情况
    private String skills;      // 个人技能
    private String selfThink;   // 自我评价

    public PersonalResume() {
    }

    public PersonalResume(String personalResumeId, String userId, String userName, String userSex, int userAge, String userPhone, String userEmail, String educationExperience, String jobTarget, String workExperience, String awards, String skills, String selfThink) {
        PersonalResumeId = personalResumeId;
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.educationExperience = educationExperience;
        this.jobTarget = jobTarget;
        this.workExperience = workExperience;
        this.awards = awards;
        this.skills = skills;
        this.selfThink = selfThink;
    }

    public String getPersonalResumeId() {
        return PersonalResumeId;
    }

    public void setPersonalResumeId(String personalResumeId) {
        PersonalResumeId = personalResumeId;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }

    public String getJobTarget() {
        return jobTarget;
    }

    public void setJobTarget(String jobTarget) {
        this.jobTarget = jobTarget;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSelfThink() {
        return selfThink;
    }

    public void setSelfThink(String selfThink) {
        this.selfThink = selfThink;
    }

    @Override
    public String toString() {
        return "PersonalResume{" +
                "PersonalResumeId='" + PersonalResumeId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", educationExperience='" + educationExperience + '\'' +
                ", jobTarget='" + jobTarget + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", awards='" + awards + '\'' +
                ", skills='" + skills + '\'' +
                ", selfThink='" + selfThink + '\'' +
                '}';
    }
}
