package com.xxx.dao.TaxRate;

import com.xxx.pojo.TaxRate;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TaxRateDaoImpl implements TaxRateDao{
    @Override
    public int deleteByPrimaryKey(String taxRateId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        int i = 0;
        try {
            i = taxRateDao.deleteByPrimaryKey(taxRateId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(TaxRate record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        int i = 0;
        try {
            i = taxRateDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(TaxRate record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        int i = 0;
        try {
            i = taxRateDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public TaxRate selectByPrimaryKey(String taxRateId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        TaxRate taxRate = null;
        try {
            taxRate = taxRateDao.selectByPrimaryKey(taxRateId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return taxRate;
    }

    @Override
    public List<TaxRate> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        List<TaxRate> taxRate = null;
        try {
            taxRate = taxRateDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return taxRate;
    }

    @Override
    public int updateByPrimaryKeySelective(TaxRate record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        int i = 0;
        try {
            i = taxRateDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(TaxRate record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TaxRateDao taxRateDao = sqlSession.getMapper(TaxRateDao.class);
        int i = 0;
        try {
            i = taxRateDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
