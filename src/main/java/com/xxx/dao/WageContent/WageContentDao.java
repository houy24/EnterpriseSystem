package com.xxx.dao.WageContent;

import com.xxx.pojo.WageContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WageContentDao {
    int deleteByPrimaryKey(String wageId);

    int insert(WageContent record);

    int insertSelective(WageContent record);

    WageContent selectByPrimaryKey(String wageId);

    List<WageContent> selectAllByUserId(String userId);

    List<WageContent> selectAll();

    int updateByPrimaryKeySelective(WageContent record);

    int updateByPrimaryKey(WageContent record);

    // 根据月份，获取当月所有的工资详情
    List<WageContent> getWageContentByMonth(String timeMonthSearch);

    // 根据月份，用户编号，获取该用户该月的工资详情
    WageContent getWageContentByUserIdAndMonth(@Param("userId") String userId, @Param("timeMonthSearch") String timeMonthSearch);

    // 统计某月份结算工资的条数
    int getCountByMonth(String timeMonthSearch);

}