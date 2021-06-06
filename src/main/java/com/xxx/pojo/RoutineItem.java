package com.xxx.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("RoutineItem")
public class RoutineItem implements Serializable {  // 日常事务工资项表
                                                    // 相当于系统常量

    private String routineItemId; // 日常事务工资项编号
    private double latePenalty; // 迟到罚金			# 100 元/次
    private double outEarlyPenalty; // 早退罚金		# 100 元/次
    private double absentPenalty; // 缺勤罚金		# 100 元/次
    private double fullTimeAllowance; // 全勤奖金    # 1000 元/月
    private double workOvertimeAllowance; // 加班奖金  # 200 元/天
    private double travelAllowance; // 出差补贴     # 200 元/天
    private double eatAllowance; // 餐饮补贴			# 300 元/月
    private double carAllowance; // 交通补贴			# 100 元/月
    private double houseAllowance; // 住房补贴		# 1000 元/月

    public RoutineItem() {
    }

    public RoutineItem(String routineItemId, double latePenalty, double outEarlyPenalty, double absentPenalty, double fullTimeAllowance, double workOvertimeAllowance, double travelAllowance, double eatAllowance, double carAllowance, double houseAllowance) {
        this.routineItemId = routineItemId;
        this.latePenalty = latePenalty;
        this.outEarlyPenalty = outEarlyPenalty;
        this.absentPenalty = absentPenalty;
        this.fullTimeAllowance = fullTimeAllowance;
        this.workOvertimeAllowance = workOvertimeAllowance;
        this.travelAllowance = travelAllowance;
        this.eatAllowance = eatAllowance;
        this.carAllowance = carAllowance;
        this.houseAllowance = houseAllowance;
    }

    public String getRoutineItemId() {
        return routineItemId;
    }

    public void setRoutineItemId(String routineItemId) {
        this.routineItemId = routineItemId;
    }

    public double getLatePenalty() {
        return latePenalty;
    }

    public void setLatePenalty(double latePenalty) {
        this.latePenalty = latePenalty;
    }

    public double getOutEarlyPenalty() {
        return outEarlyPenalty;
    }

    public void setOutEarlyPenalty(double outEarlyPenalty) {
        this.outEarlyPenalty = outEarlyPenalty;
    }

    public double getAbsentPenalty() {
        return absentPenalty;
    }

    public void setAbsentPenalty(double absentPenalty) {
        this.absentPenalty = absentPenalty;
    }

    public double getFullTimeAllowance() {
        return fullTimeAllowance;
    }

    public void setFullTimeAllowance(double fullTimeAllowance) {
        this.fullTimeAllowance = fullTimeAllowance;
    }

    public double getWorkOvertimeAllowance() {
        return workOvertimeAllowance;
    }

    public void setWorkOvertimeAllowance(double workOvertimeAllowance) {
        this.workOvertimeAllowance = workOvertimeAllowance;
    }

    public double getTravelAllowance() {
        return travelAllowance;
    }

    public void setTravelAllowance(double travelAllowance) {
        this.travelAllowance = travelAllowance;
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

    public double getHouseAllowance() {
        return houseAllowance;
    }

    public void setHouseAllowance(double houseAllowance) {
        this.houseAllowance = houseAllowance;
    }

    @Override
    public String toString() {
        return "RoutineItem{" +
                "routineItemId='" + routineItemId + '\'' +
                ", latePenalty=" + latePenalty +
                ", outEarlyPenalty=" + outEarlyPenalty +
                ", absentPenalty=" + absentPenalty +
                ", fullTimeAllowance=" + fullTimeAllowance +
                ", workOvertimeAllowance=" + workOvertimeAllowance +
                ", travelAllowance=" + travelAllowance +
                ", eatAllowance=" + eatAllowance +
                ", carAllowance=" + carAllowance +
                ", houseAllowance=" + houseAllowance +
                '}';
    }
}
