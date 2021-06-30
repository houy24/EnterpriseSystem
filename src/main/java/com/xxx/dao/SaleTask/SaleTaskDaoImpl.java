package com.xxx.dao.SaleTask;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.pojo.Department;
import com.xxx.pojo.SaleTask;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SaleTaskDaoImpl implements SaleTaskDao{
    @Override
    public int deleteByPrimaryKey(String saleTaskId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        int i = 0;
        try {
            i = saleTaskDao.deleteByPrimaryKey(saleTaskId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(SaleTask record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        int i = 0;
        try {
            i = saleTaskDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(SaleTask record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        int i = 0;
        try {
            i = saleTaskDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public SaleTask selectByPrimaryKey(String saleTaskId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        SaleTask saleTask = null;
        try {
            saleTask = saleTaskDao.selectByPrimaryKey(saleTaskId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return saleTask;
    }

    @Override
    public List<SaleTask> selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        List<SaleTask> saleTask = null;
        try {
            saleTask = saleTaskDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return saleTask;
    }

    @Override
    public List<SaleTask> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        List<SaleTask> saleTask = null;
        try {
            saleTask = saleTaskDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return saleTask;
    }

    @Override
    public int updateByPrimaryKeySelective(SaleTask record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        int i = 0;
        try {
            i = saleTaskDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(SaleTask record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleTaskDao saleTaskDao = sqlSession.getMapper(SaleTaskDao.class);
        int i = 0;
        try {
            i = saleTaskDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
