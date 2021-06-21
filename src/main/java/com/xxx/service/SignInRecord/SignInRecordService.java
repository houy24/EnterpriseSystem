package com.xxx.service.SignInRecord;

import com.xxx.pojo.SignInRecord;

import java.util.List;

public interface SignInRecordService {

    // 获取所有签到记录
    List<SignInRecord> getAllSignInRecord();

    // 获取某个月份的签到信息  （格式：2021-05）
    List<SignInRecord> getSignInRecordByMonth(String timeMonthSearch);

    // 获取某天的签到记录信息  （格式：2020-05-11）
    List<SignInRecord> getSignInRecordByDay(String timeDaySearch);

    // 获取某天的某个用户的签到记录信息 （格式：2020-05-11，user-1）
    SignInRecord getSignInRecordByDayAndUserId(String timeDaySearch, String userId);

}
