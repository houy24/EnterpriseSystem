package com.xxx.dao.Department;

import com.xxx.pojo.Department;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao{
    @Override
    public int deleteByPrimaryKey(String departmentId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        int i = 0;
        try {
            i = departmentDao.deleteByPrimaryKey(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(Department record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        int i = 0;
        try {
            i = departmentDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(Department record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        int i = 0;
        try {
            i = departmentDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public Department selectByPrimaryKey(String departmentId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        Department department = null;
        try {
            department = departmentDao.selectByPrimaryKey(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return department;
    }

    @Override
    public List<Department> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        List<Department> department = null;
        try {
            department = departmentDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return department;
    }

    @Override
    public int updateByPrimaryKeySelective(Department record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        int i = 0;
        try {
            i = departmentDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
        int i = 0;
        try {
            i = departmentDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
