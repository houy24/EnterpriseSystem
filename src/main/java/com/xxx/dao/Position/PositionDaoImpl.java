package com.xxx.dao.Position;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.pojo.Department;
import com.xxx.pojo.Position;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PositionDaoImpl implements PositionDao{
    @Override
    public int deleteByPrimaryKey(String positionId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => deleteByPrimaryKey");
        int i = 0;
        try {
            i = positionDao.deleteByPrimaryKey(positionId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(Position record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => insert");
        int i = 0;
        try {
            i = positionDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(Position record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => insertSelective");
        int i = 0;
        try {
            i = positionDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public Position selectByPrimaryKey(String positionId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => selectByPrimaryKey");
        Position position = null;
        try {
            position = positionDao.selectByPrimaryKey(positionId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return position;
    }

    @Override
    public List<Position> selectAllByPositionDesrciption(String positionDesrciption) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => selectAllByPositionDesrciption");
        List<Position> position = null;
        try {
            position = positionDao.selectAllByPositionDesrciption(positionDesrciption);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return position;
    }

    @Override
    public int updateByPrimaryKeySelective(Position record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => updateByPrimaryKeySelective");
        int i = 0;
        try {
            i = positionDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Position record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PositionDao positionDao = sqlSession.getMapper(PositionDao.class);
        System.out.println("PositionDaoImpl => updateByPrimaryKey");
        int i = 0;
        try {
            i = positionDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }
}
