package com.xxx.dao.SignInRecord;

import com.xxx.dao.PersonalResume.PersonalResumeDao;
import com.xxx.pojo.PersonalResume;
import com.xxx.pojo.SignInRecord;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SignInRecordDaoImpl implements SignInRecordDao{
    @Override
    public int deleteByPrimaryKey(String signInId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.deleteByPrimaryKey(signInId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(SignInRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(SignInRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public SignInRecord selectByPrimaryKey(String signInId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        SignInRecord signInRecord = null;
        try {
            signInRecord = signInRecordDao.selectByPrimaryKey(signInId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return signInRecord;
    }

    @Override
    public List<SignInRecord> selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        List<SignInRecord> signInRecord = null;
        try {
            signInRecord = signInRecordDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return signInRecord;
    }

    @Override
    public int updateByPrimaryKeySelective(SignInRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(SignInRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
    // 获取所有签到信息
    @Override
    public List<SignInRecord> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        List<SignInRecord> signInRecordList = null;
        try {
            signInRecordList = signInRecordDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return signInRecordList;
    }
    //查询迟到
    @Override
    public List<SignInRecord> selectAllByUserIdLate(String userId){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        List<SignInRecord> signInRecords = null;
        try{
            signInRecords = signInRecordDao.selectAllByUserIdLate(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return signInRecords;
    }
    //查询早退
    @Override
    public List<SignInRecord> selectAllByUserIdLeaveEarly(String userId){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        List<SignInRecord> signInRecords = null;
        try{
            signInRecords = signInRecordDao.selectAllByUserIdLeaveEarly(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return signInRecords;
    }
    @Override
    public List<SignInRecord> selectAllByUserName(String userName){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        List<SignInRecord> signInRecordList = null;
        try{
            signInRecordList = signInRecordDao.selectAllByUserName(userName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return signInRecordList;
    }
    @Override
    public int selectDayByUserId(String userId){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        int i = 0;
        try {
            i = signInRecordDao.selectDayByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
    @Override
    public SignInRecord selectTodayByUserId(String userId){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SignInRecordDao signInRecordDao = sqlSession.getMapper(SignInRecordDao.class);
        SignInRecord signInRecord = null;
        try {
            signInRecord = signInRecordDao.selectTodayByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return signInRecord;
    }
}
