package com.xxx.dao.WageContent;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.pojo.Department;
import com.xxx.pojo.WageContent;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WageContentDaoImpl implements WageContentDao{
    @Override
    public int deleteByPrimaryKey(String wageId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = wageContentDao.deleteByPrimaryKey(wageId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(WageContent record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => insert");
        int i = 0;
        try {
            i = wageContentDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(WageContent record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => insertSelective");
        int i = 0;
        try {
            i = wageContentDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public WageContent selectByPrimaryKey(String wageId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => selectByPrimaryKey");
        WageContent wageContent = null;
        try {
            wageContent = wageContentDao.selectByPrimaryKey(wageId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wageContent;
    }

    @Override
    public List<WageContent> selectAllByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => selectAllByUserId");
        List<WageContent> wageContent = null;
        try {
            wageContent = wageContentDao.selectAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wageContent;
    }

    @Override
    public List<WageContent> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => selectAll");
        List<WageContent> wageContent = null;
        try {
            wageContent = wageContentDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return wageContent;
    }

    @Override
    public int updateByPrimaryKeySelective(WageContent record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WageContentDao wageContentDao = sqlSession.getMapper(WageContentDao.class);
        System.out.println("WageContentDaoImpl => updateByPrimaryKeySelective");
        int i = 0;
        try {
            i = wageContentDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(WageContent record) {
        return 0;
    }
}
