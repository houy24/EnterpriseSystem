package com.xxx.dao.ProductWarehouse;

import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.pojo.ProductType;
import com.xxx.pojo.ProductWarehouse;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class ProductWarehouseDaoImpl implements ProductWarehouseDao{
    @Override
    public int deleteByPrimaryKey(String productWarehouseId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        int i = 0;
        try {
            i = productWarehouseDao.deleteByPrimaryKey(productWarehouseId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(ProductWarehouse record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        int i = 0;
        try {
            i = productWarehouseDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(ProductWarehouse record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        int i = 0;
        try {
            i = productWarehouseDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public ProductWarehouse selectByPrimaryKey(String productWarehouseId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        ProductWarehouse productWarehouse = null;
        try {
            productWarehouse = productWarehouseDao.selectByPrimaryKey(productWarehouseId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productWarehouse;
    }

    @Override
    public List<ProductWarehouse> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        List<ProductWarehouse> productWarehouse = null;
        try {
            productWarehouse = productWarehouseDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productWarehouse;
    }

    @Override
    public List<ProductWarehouse> selectAllByProductId(String productId) {
        System.out.println(productId);
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        List<ProductWarehouse> productWarehouse = null;
        try {
            productWarehouse = productWarehouseDao.selectAllByProductId(productId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productWarehouse;
    }

    @Override
    public List<ProductWarehouse> selectAllByProductName(String productName) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        List<ProductWarehouse> productWarehouse = null;
        try {
            productWarehouse = productWarehouseDao.selectAllByProductName(productName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productWarehouse;
    }

    @Override
    public int updateByPrimaryKeySelective(ProductWarehouse record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductWarehouseDao productWarehouseDao = sqlSession.getMapper(ProductWarehouseDao.class);
        int i = 0;
        try {
            i = productWarehouseDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(ProductWarehouse record) {
        return 0;
    }
}
