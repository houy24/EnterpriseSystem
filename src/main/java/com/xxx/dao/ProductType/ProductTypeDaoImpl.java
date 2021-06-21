package com.xxx.dao.ProductType;

import com.xxx.dao.Product.ProductDao;
import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductTypeDaoImpl implements ProductTypeDao{
    @Override
    public int deleteByPrimaryKey(String productTypeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = productDao.deleteByPrimaryKey(productTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(ProductType record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
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
    public int insertSelective(ProductType record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductDaoImpl => insertSelective");
        int i = 0;
        try {
            i = productDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public ProductType selectByPrimaryKey(String productTypeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductTypeDaoImpl => selectByPrimaryKey");
        ProductType productType = null;
        try {
            productType = productDao.selectByPrimaryKey(productTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productType;
    }

    @Override
    public List<ProductType> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductTypeDaoImpl => selectAll");
        List<ProductType> productTypes = null;
        try {
            productTypes = productDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productTypes;
    }

    @Override
    public ProductType selectByProductTypeName(String productTypeName) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductTypeDaoImpl => selectByProductTypeName");
        ProductType productType = null;
        try {
            productType = productDao.selectByProductTypeName(productTypeName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productType;
    }

    @Override
    public ProductType getByProductTypeName(String productTypeName) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductTypeDaoImpl => getByProductTypeName");
        ProductType productType = null;
        try {
            productType = productDao.getByProductTypeName(productTypeName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return productType;
    }

    @Override
    public int updateByPrimaryKeySelective(ProductType record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
        System.out.println("ProductDaoImpl => updateByPrimaryKeySelective");
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
    public int updateByPrimaryKey(ProductType record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductTypeDao productDao = sqlSession.getMapper(ProductTypeDao.class);
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
