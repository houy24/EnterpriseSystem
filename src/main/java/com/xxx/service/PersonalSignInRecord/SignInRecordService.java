package com.xxx.service.PersonalSignInRecord;

import com.xxx.pojo.SignInRecord;

import java.util.List;

public interface SignInRecordService {
    //查询所以考勤记录
    List<SignInRecord> selectAllByUserId(String userId);
    //查询迟到迟到
    List<SignInRecord> selectAllByUserIdLate(String useId);
    //查询早退
    List<SignInRecord> selectAllByUserIdLeaveEarly(String userId);
    //查询所有员工考勤记录
    List<SignInRecord> selectAll();
    //模糊查询员工考勤记录
    List<SignInRecord> selectAllByUserName(String userName);
    //更新考勤记录
    int updateByPrimaryKey(SignInRecord record);
    //添加考勤记录
    int insert(SignInRecord record);
    //删除考勤记录
    int deleteByPrimaryKey(String signInId);
    //查询当天签到
    int selectDayByUserId(String userId);
    //添加签到记录
    int insertSelective(SignInRecord record);
    //签退记录
    int updateByPrimaryKeySelective(SignInRecord record);
    //查询当天记录
    SignInRecord selectTodayByUserId(String userId);
}
