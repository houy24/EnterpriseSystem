package com.xxx.dao.RoutineItem;

import com.xxx.pojo.RoutineItem;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class RoutineItemDaoImpl implements RoutineItemDao{
    @Override
    public int deleteByPrimaryKey(String routineItemId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        int i = 0;
        try {
            i = routineItemDao.deleteByPrimaryKey(routineItemId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(RoutineItem record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        int i = 0;
        try {
            i = routineItemDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(RoutineItem record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        int i = 0;
        try {
            i = routineItemDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public RoutineItem selectByPrimaryKey(String routineItemId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        RoutineItem routineItem = null;
        try {
            routineItem = routineItemDao.selectByPrimaryKey(routineItemId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return routineItem;
    }

    @Override
    public int updateByPrimaryKeySelective(RoutineItem record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        int i = 0;
        try {
            i = routineItemDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(RoutineItem record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoutineItemDao routineItemDao = sqlSession.getMapper(RoutineItemDao.class);
        int i = 0;
        try {
            i = routineItemDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
