package com.xxx.dao.PersonalResume;

import com.xxx.dao.Product.ProductDao;
import com.xxx.pojo.PersonalResume;
import com.xxx.pojo.Product;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PersonalResumeDaoImpl implements PersonalResumeDao{
    @Override
    public int deleteByPrimaryKey(String personalResumeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        int i = 0;
        try {
            i = personalResumeDao.deleteByPrimaryKey(personalResumeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(PersonalResume record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        int i = 0;
        try {
            i = personalResumeDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(PersonalResume record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        int i = 0;
        try {
            i = personalResumeDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public PersonalResume selectByPrimaryKey(String personalResumeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        PersonalResume personalResume = null;
        try {
            personalResume = personalResumeDao.selectByPrimaryKey(personalResumeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return personalResume;
    }

    @Override
    public PersonalResume selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        PersonalResume personalResume = null;
        try {
            personalResume = personalResumeDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return personalResume;
    }

    @Override
    public List<PersonalResume> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        List<PersonalResume> personalResume = null;
        try {
            personalResume = personalResumeDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return personalResume;
    }

    @Override
    public int updateByPrimaryKeySelective(PersonalResume record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        int i = 0;
        try {
            i = personalResumeDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(PersonalResume record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PersonalResumeDao personalResumeDao = sqlSession.getMapper(PersonalResumeDao.class);
        int i = 0;
        try {
            i = personalResumeDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
