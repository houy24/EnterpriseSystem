package com.xxx.service.Wage;

import com.xxx.pojo.Wage;
import com.xxx.pojo.WageContent;

import java.util.List;

public interface WageService {

    // 获取所有工资列表
    List<Wage> getAllWage();

    // 根据工资编号 获取工资
    Wage getWageById(String wageId);

    // 根据用户编号，获取工资列表
    List<Wage> getWageByuserId(String userId);

    // 根据月份，获取工资列表
    List<Wage> getWageByMonth(String timeMonthSearch);

    // 根据月份，用户编号，获取该用户该月的工资
    Wage getWageByUserIdAndMonth(String userId, String timeMonthSearch);

    // 判断某月工资是否结算
    boolean judgeExistWageByMonth(String timeMonthSearch);




    // 根据月份，用户编号，获取该用户，该月份的工资
    double getUserMonthWage(String userId,String timeMonthSearch);

    // 根据年份，用户编号，获取该用户，该年份的总工资
    double getUserYearWage(String userId,String timeYearSearch);

    // 根据 部门编号，年份，获取该部门，该年份的总工资
    double getDepartmentYearWage(String departmentId,String timeYearSearch);









}
