package com.xxx.dao.Wage;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.pojo.Department;
import com.xxx.pojo.Wage;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WageDaoImpl implements WageDao{
    @Override
    public int deleteByPrimaryKey(String wageId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = wageDao.deleteByPrimaryKey(wageId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(Wage record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => insert");
        int i = 0;
        try {
            i = wageDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(Wage record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => insertSelective");
        int i = 0;
        try {
            i = wageDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public Wage selectByPrimaryKey(String wageId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => selectByPrimaryKey");
        Wage wage = null;
        try {
            wage = wageDao.selectByPrimaryKey(wageId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wage;
    }

    @Override
    public List<Wage> selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => selectAllByUserId");
        List<Wage> wage = null;
        try {
            wage = wageDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wage;
    }

    @Override
    public int updateByPrimaryKeySelective(Wage record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => updateByPrimaryKeySelective");
        int i = 0;
        try {
            i = wageDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Wage record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => updateByPrimaryKey");
        int i = 0;
        try {
            i = wageDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public List<Wage> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => selectAll");
        List<Wage> wage = null;
        try {
            wage = wageDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wage;
    }

    @Override
    public List<Wage> getWageByMonth(String timeMonthSearch) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => getWageByMonth");
        List<Wage> wage = null;
        try {
            wage = wageDao.getWageByMonth(timeMonthSearch);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wage;
    }

    @Override
    public Wage getWageByUserIdAndMonth(String userId, String timeMonthSearch) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => getWageByUserIdAndMonth");
        Wage wage = null;
        try {
            wage = wageDao.getWageByUserIdAndMonth(userId, timeMonthSearch);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wage;
    }

    @Override
    public int getCountByMonth(String timeMonthSearch) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageDao wageDao = sqlSession.getMapper(WageDao.class);
        System.out.println("WageDaoImpl => getCountByMonth");
        int i = 0;
        try {
            i = wageDao.getCountByMonth(timeMonthSearch);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
