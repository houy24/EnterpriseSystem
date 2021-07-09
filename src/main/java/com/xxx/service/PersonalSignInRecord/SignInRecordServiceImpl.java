package com.xxx.service.PersonalSignInRecord;

import com.xxx.dao.SignInRecord.SignInRecordDao;
import com.xxx.dao.SignInRecord.SignInRecordDaoImpl;
import com.xxx.pojo.SignInRecord;

import java.util.List;

public class SignInRecordServiceImpl implements SignInRecordService{
    private SignInRecordDao signInRecordDao =null;
    public SignInRecordServiceImpl(){
        signInRecordDao = new SignInRecordDaoImpl();
    }
    //查询考勤
    @Override
    public List<SignInRecord> selectAllByUserId(String userId) {
        return signInRecordDao.selectAllByUserId(userId);
    }
    //查询迟到
    @Override
    public List<SignInRecord> selectAllByUserIdLate(String userId){
        return signInRecordDao.selectAllByUserIdLate(userId);
    }
    //查询早退
    @Override
    public List<SignInRecord> selectAllByUserIdLeaveEarly(String userId){
        return signInRecordDao.selectAllByUserIdLeaveEarly(userId);
    }
    //查询所有员工考勤记录
    @Override
    public  List<SignInRecord> selectAll(){
        return signInRecordDao.selectAll();
    }
    //模糊查询员工考勤记录
    @Override
    public List<SignInRecord> selectAllByUserName(String userName){
        return signInRecordDao.selectAllByUserName(userName);
    }
    //更新考勤记录
    @Override
    public int updateByPrimaryKey(SignInRecord record){
        return signInRecordDao.updateByPrimaryKey(record);
    }
    //增加考勤记录
    @Override
    public int insert(SignInRecord record){
        return signInRecordDao.insert(record);
    }
    //删除考勤记录
    @Override
    public int deleteByPrimaryKey(String signInId){
        return signInRecordDao.deleteByPrimaryKey(signInId);
    }
    //查询当天签到
    @Override
    public int selectDayByUserId(String userId){
        return signInRecordDao.selectDayByUserId(userId);
    }
    //添加签到记录
    @Override
    public  int insertSelective(SignInRecord record){
        return signInRecordDao.insertSelective(record);
    }
    //签退
    @Override
    public int updateByPrimaryKeySelective(SignInRecord record){
        return signInRecordDao.updateByPrimaryKeySelective(record);
    }
    @Override
    public SignInRecord selectTodayByUserId(String userId){
        return signInRecordDao.selectTodayByUserId(userId);
    }
}
