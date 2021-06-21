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

}