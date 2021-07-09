package com.xxx.dao.SignInRecord;

import com.xxx.pojo.SignInRecord;

import java.util.List;

public interface SignInRecordDao {
    int deleteByPrimaryKey(String signInId);

    int insert(SignInRecord record);

    int insertSelective(SignInRecord record);

    SignInRecord selectByPrimaryKey(String signInId);

    List<SignInRecord> selectAllByUserId(String userId);

    int updateByPrimaryKeySelective(SignInRecord record);

    int updateByPrimaryKey(SignInRecord record);

    // 获取所有签到信息
    List<SignInRecord> selectAll();

    //查询迟到迟到
    List<SignInRecord> selectAllByUserIdLate(String useId);
    //查询早退
    List<SignInRecord> selectAllByUserIdLeaveEarly(String userId);
    //模糊查询员工考勤记录
    List<SignInRecord> selectAllByUserName(String userName);
    //查询当天签到
    int selectDayByUserId(String userId);
    //查询当天的记录
    SignInRecord selectTodayByUserId(String userId);
}