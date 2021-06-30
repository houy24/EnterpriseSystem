package com.xxx.dao.Product;

import com.xxx.dao.UserData.UserDataDao;
import com.xxx.pojo.Product;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public int deleteByPrimaryKey(String productId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        int i = 0;
        try {
            i = productDao.deleteByPrimaryKey(productId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(Product record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        int i = 0;
        try {
            i = productDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public Product selectByPrimaryKey(String productId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        Product product = null;
        try {
            product = productDao.selectByPrimaryKey(productId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return product;
    }

    @Override
    public Product selectPyProductName(String productName) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        Product product = null;
        try {
            product = productDao.selectPyProductName(productName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return product;
    }

    @Override
    public List<Product> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        List<Product> products = null;
        try {
            products = productDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return products;
    }

    @Override
    public List<Product> selectByProductTypeId(String productTypeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        List<Product> products = null;
        try {
            products = productDao.selectByProductTypeId(productTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return products;
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        int i = 0;
        try {
            i = productDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        int i = 0;
        try {
            i = productDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
