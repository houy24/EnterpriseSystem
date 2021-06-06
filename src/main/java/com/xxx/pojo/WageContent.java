package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("WageContent")
public class WageContent implements Serializable {  // 工资详情表

    private String wageId; // 工资编号      # 随机生成UUID
    private String userId; // 用户编号
    private Date wageProvideTime; // 工资发放时间

    private double shouldWage; // 应发工资       # 工资情况
    private double realyWage; // 实发工资

    private double positionMoney; // 岗位工资	# 基本工资
    private double workResultMoney; //	 绩效工资
    private double workAgeMoney; //  工龄工资

    private double fullTimeMoney; // 全勤工资	# 奖金
    private double workTitleMoney; //  职称工资
    private double workOverTimeMoney; //  加班工资

    private double eatAllowance; //  餐饮补贴		# 补贴
    private double carAllowance; //  交通补贴
    private double travelAllowance; //  出差补贴
    private double houseAllowance; // 住房补贴

    private double lateMoney; //  迟到罚金	# 缺勤罚金
    private double outEarlyMoney; // 早退罚金
    private double absentMoney; // 缺勤罚金

    private double oldEnsure; // 养老保险	# 五险一金
    private double medicalEnsure; //  医疗保险
    private double lostJobEnsure; // 失业保险
    private double workHurtEnsure; // 工伤保险
    private double birthEnsure; // 生育保险
    private double houseFundEnsure; // 住房公积金

    private double oneSelfTax; //  个人所得税'		 # 纳税

    public WageContent() {
    }

    public WageContent(String wageId, String userId, Date wageProvideTime, double shouldWage, double realyWage, double positionMoney, double workResultMoney, double workAgeMoney, double fullTimeMoney, double workTitleMoney, double workOverTimeMoney, double eatAllowance, double carAllowance, double travelAllowance, double houseAllowance, double lateMoney, double outEarlyMoney, double absentMoney, double oldEnsure, double medicalEnsure, double lostJobEnsure, double workHurtEnsure, double birthEnsure, double houseFundEnsure, double oneSelfTax) {
        this.wageId = wageId;
        this.userId = userId;
        this.wageProvideTime = wageProvideTime;
        this.shouldWage = shouldWage;
        this.realyWage = realyWage;
        this.positionMoney = positionMoney;
        this.workResultMoney = workResultMoney;
        this.workAgeMoney = workAgeMoney;
        this.fullTimeMoney = fullTimeMoney;
        this.workTitleMoney = workTitleMoney;
        this.workOverTimeMoney = workOverTimeMoney;
        this.eatAllowance = eatAllowance;
        this.carAllowance = carAllowance;
        this.travelAllowance = travelAllowance;
        this.houseAllowance = houseAllowance;
        this.lateMoney = lateMoney;
        this.outEarlyMoney = outEarlyMoney;
        this.absentMoney = absentMoney;
        this.oldEnsure = oldEnsure;
        this.medicalEnsure = medicalEnsure;
        this.lostJobEnsure = lostJobEnsure;
        this.workHurtEnsure = workHurtEnsure;
        this.birthEnsure = birthEnsure;
        this.houseFundEnsure = houseFundEnsure;
        this.oneSelfTax = oneSelfTax;
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

    public Date getWageProvideTime() {
        return wageProvideTime;
    }

    public void setWageProvideTime(Date wageProvideTime) {
        this.wageProvideTime = wageProvideTime;
    }

    public double getShouldWage() {
        return shouldWage;
    }

    public void setShouldWage(double shouldWage) {
        this.shouldWage = shouldWage;
    }

    public double getRealyWage() {
        return realyWage;
    }

    public void setRealyWage(double realyWage) {
        this.realyWage = realyWage;
    }

    public double getPositionMoney() {
        return positionMoney;
    }

    public void setPositionMoney(double positionMoney) {
        this.positionMoney = positionMoney;
    }

    public double getWorkResultMoney() {
        return workResultMoney;
    }

    public void setWorkResultMoney(double workResultMoney) {
        this.workResultMoney = workResultMoney;
    }

    public double getWorkAgeMoney() {
        return workAgeMoney;
    }

    public void setWorkAgeMoney(double workAgeMoney) {
        this.workAgeMoney = workAgeMoney;
    }

    public double getFullTimeMoney() {
        return fullTimeMoney;
    }

    public void setFullTimeMoney(double fullTimeMoney) {
        this.fullTimeMoney = fullTimeMoney;
    }

    public double getWorkTitleMoney() {
        return workTitleMoney;
    }

    public void setWorkTitleMoney(double workTitleMoney) {
        this.workTitleMoney = workTitleMoney;
    }

    public double getWorkOverTimeMoney() {
        return workOverTimeMoney;
    }

    public void setWorkOverTimeMoney(double workOverTimeMoney) {
        this.workOverTimeMoney = workOverTimeMoney;
    }

    public double getEatAllowance() {
        return eatAllowance;
    }

    public void setEatAllowance(double eatAllowance) {
        this.eatAllowance = eatAllowance;
    }

    public double getCarAllowance() {
        return carAllowance;
    }

    public void setCarAllowance(double carAllowance) {
        this.carAllowance = carAllowance;
    }

    public double getTravelAllowance() {
        return travelAllowance;
    }

    public void setTravelAllowance(double travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public double getHouseAllowance() {
        return houseAllowance;
    }

    public void setHouseAllowance(double houseAllowance) {
        this.houseAllowance = houseAllowance;
    }

    public double getLateMoney() {
        return lateMoney;
    }

    public void setLateMoney(double lateMoney) {
        this.lateMoney = lateMoney;
    }

    public double getOutEarlyMoney() {
        return outEarlyMoney;
    }

    public void setOutEarlyMoney(double outEarlyMoney) {
        this.outEarlyMoney = outEarlyMoney;
    }

    public double getAbsentMoney() {
        return absentMoney;
    }

    public void setAbsentMoney(double absentMoney) {
        this.absentMoney = absentMoney;
    }

    public double getOldEnsure() {
        return oldEnsure;
    }

    public void setOldEnsure(double oldEnsure) {
        this.oldEnsure = oldEnsure;
    }

    public double getMedicalEnsure() {
        return medicalEnsure;
    }

    public void setMedicalEnsure(double medicalEnsure) {
        this.medicalEnsure = medicalEnsure;
    }

    public double getLostJobEnsure() {
        return lostJobEnsure;
    }

    public void setLostJobEnsure(double lostJobEnsure) {
        this.lostJobEnsure = lostJobEnsure;
    }

    public double getWorkHurtEnsure() {
        return workHurtEnsure;
    }

    public void setWorkHurtEnsure(double workHurtEnsure) {
        this.workHurtEnsure = workHurtEnsure;
    }

    public double getBirthEnsure() {
        return birthEnsure;
    }

    public void setBirthEnsure(double birthEnsure) {
        this.birthEnsure = birthEnsure;
    }

    public double getHouseFundEnsure() {
        return houseFundEnsure;
    }

    public void setHouseFundEnsure(double houseFundEnsure) {
        this.houseFundEnsure = houseFundEnsure;
    }

    public double getOneSelfTax() {
        return oneSelfTax;
    }

    public void setOneSelfTax(double oneSelfTax) {
        this.oneSelfTax = oneSelfTax;
    }

    @Override
    public String toString() {
        return "WageContent{" +
                "wageId='" + wageId + '\'' +
                ", userId='" + userId + '\'' +
                ", wageProvideTime=" + wageProvideTime +
                ", shouldWage=" + shouldWage +
                ", realyWage=" + realyWage +
                ", positionMoney=" + positionMoney +
                ", workResultMoney=" + workResultMoney +
                ", workAgeMoney=" + workAgeMoney +
                ", fullTimeMoney=" + fullTimeMoney +
                ", workTitleMoney=" + workTitleMoney +
                ", workOverTimeMoney=" + workOverTimeMoney +
                ", eatAllowance=" + eatAllowance +
                ", carAllowance=" + carAllowance +
                ", travelAllowance=" + travelAllowance +
                ", houseAllowance=" + houseAllowance +
                ", lateMoney=" + lateMoney +
                ", outEarlyMoney=" + outEarlyMoney +
                ", absentMoney=" + absentMoney +
                ", oldEnsure=" + oldEnsure +
                ", medicalEnsure=" + medicalEnsure +
                ", lostJobEnsure=" + lostJobEnsure +
                ", workHurtEnsure=" + workHurtEnsure +
                ", birthEnsure=" + birthEnsure +
                ", houseFundEnsure=" + houseFundEnsure +
                ", oneSelfTax=" + oneSelfTax +
                '}';
    }
}
