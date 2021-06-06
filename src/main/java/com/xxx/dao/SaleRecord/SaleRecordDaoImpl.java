package com.xxx.dao.SaleRecord;

import com.xxx.pojo.SaleRecord;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SaleRecordDaoImpl implements SaleRecordDao{
    @Override
    public int deleteByPrimaryKey(String saleRecordId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = saleRecordDao.deleteByPrimaryKey(saleRecordId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(SaleRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => insert");
        int i = 0;
        try {
            i = saleRecordDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(SaleRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => insertSelective");
        int i = 0;
        try {
            i = saleRecordDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public SaleRecord selectByPrimaryKey(String saleRecordId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => selectByPrimaryKey");
        SaleRecord saleRecord = null;
        try {
            saleRecord = saleRecordDao.selectByPrimaryKey(saleRecordId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return saleRecord;
    }

    @Override
    public List<SaleRecord> selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => selectAllByUserId");
        List<SaleRecord> saleRecords = null;
        try {
            saleRecords = saleRecordDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return saleRecords;
    }

    @Override
    public int updateSaleRecordStateBySaleRecordId(String saleRecordState, String saleRecordId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => updateSaleRecordStateBySaleRecordId");
        int i = 0;
        try {
            i = saleRecordDao.updateSaleRecordStateBySaleRecordId(saleRecordState, saleRecordId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(SaleRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => updateByPrimaryKeySelective");
        int i = 0;
        try {
            i = saleRecordDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(SaleRecord record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SaleRecordDao saleRecordDao = sqlSession.getMapper(SaleRecordDao.class);
        System.out.println("SaleRecordDaoImpl => updateByPrimaryKey");
        int i = 0;
        try {
            i = saleRecordDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
