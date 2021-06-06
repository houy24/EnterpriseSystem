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
        System.out.println("ProductDaoImpl => deleteByPrimaryKey");
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
        System.out.println("ProductDaoImpl => insert");
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
        System.out.println("ProductDaoImpl => selectByPrimaryKey");
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
    public List<Product> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        System.out.println("ProductDaoImpl => selectAll");
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
    public int updateByPrimaryKeySelective(Product record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        System.out.println("ProductDaoImpl => selectByPrimaryKey");
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
        System.out.println("ProductDaoImpl => updateByPrimaryKey");
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
