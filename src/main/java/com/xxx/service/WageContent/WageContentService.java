package com.xxx.service.WageContent;

import com.xxx.pojo.WageContent;

import java.text.ParseException;
import java.util.List;

public interface WageContentService {

    // 获取所有工资详情列表
    List<WageContent> getAllWageContent();

    // 根据工资编号 获取工资详情
    WageContent getWageContentById(String wageId);

    // 根据用户编号，获取工资详情列表
    List<WageContent> getWageContentByuserId(String userId);

    // 根据月份，获取工资详情列表
    List<WageContent> getWageContentByMonth(String timeMonthSearch);

    // 根据月份，用户编号，获取该用户该月的工资详情
    WageContent getWageContentByUserIdAndMonth(String userId, String timeMonthSearch);

    // 判断某月工资是否结算
    boolean judgeExistWageContentByMonth(String timeMonthSearch);


    // 插入一条 工资表 ，和 工资详情表
    boolean insertWageAndWageContent(WageContent wageContent);

    // 删除一条 工资表 ， 和 工资详情表
    boolean deleteWageAndWageContentByWageId(String wageId);

    // 更新一条 工资表 ， 和 工资详情表
    boolean updateWageAndWageContent(WageContent wageContent);

    // 计算某个用户该月工资详情
    WageContent calculateWageContentByUserIdAndMonth(String userId, String timeMonthSearch) throws ParseException;

}
