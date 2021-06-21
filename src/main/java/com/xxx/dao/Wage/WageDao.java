package com.xxx.dao.Wage;

import com.xxx.pojo.Wage;
import com.xxx.pojo.WageContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WageDao {
    int deleteByPrimaryKey(String wageId);

    int insert(Wage record);

    int insertSelective(Wage record);

    Wage selectByPrimaryKey(String wageId);

    List<Wage> selectAllByUserId(String userId);

    int updateByPrimaryKeySelective(Wage record);

    int updateByPrimaryKey(Wage record);

    // 查询所有工资
    List<Wage> selectAll();

    // 根据月份，获取当月所有的工资
    List<Wage> getWageByMonth(String timeMonthSearch);

    // 根据月份，用户编号，获取该用户该月的工资
    Wage getWageByUserIdAndMonth(@Param("userId") String userId, @Param("timeMonthSearch") String timeMonthSearch);

    // 统计某月份结算工资的条数
    int getCountByMonth(String timeMonthSearch);

}