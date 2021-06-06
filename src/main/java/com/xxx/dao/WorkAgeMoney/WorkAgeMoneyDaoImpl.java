package com.xxx.dao.WorkAgeMoney;

import com.xxx.dao.WorkTitle.WorkTitleDao;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.pojo.WorkTitle;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WorkAgeMoneyDaoImpl implements WorkAgeMoneyDao{
    @Override
    public int deleteByPrimaryKey(int workAge) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = workAgeMoneyDao.deleteByPrimaryKey(workAge);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(WorkAgeMoney record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => insert");
        int i = 0;
        try {
            i = workAgeMoneyDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(WorkAgeMoney record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => insertSelective");
        int i = 0;
        try {
            i = workAgeMoneyDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public WorkAgeMoney selectByPrimaryKey(int workAge) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => selectByPrimaryKey");
        WorkAgeMoney workAgeMoney = null;
        try {
            workAgeMoney = workAgeMoneyDao.selectByPrimaryKey(workAge);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return workAgeMoney;
    }

    @Override
    public List<WorkAgeMoney> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => selectAll");
        List<WorkAgeMoney> workAgeMoney = null;
        try {
            workAgeMoney = workAgeMoneyDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return workAgeMoney;
    }

    @Override
    public int updateByPrimaryKeySelective(WorkAgeMoney record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => updateByPrimaryKeySelective");
        int i = 0;
        try {
            i = workAgeMoneyDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(WorkAgeMoney record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkAgeMoneyDao workAgeMoneyDao = sqlSession.getMapper(WorkAgeMoneyDao.class);
        System.out.println("WorkAgeMoneyDaoImpl => updateByPrimaryKey");
        int i = 0;
        try {
            i = workAgeMoneyDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
