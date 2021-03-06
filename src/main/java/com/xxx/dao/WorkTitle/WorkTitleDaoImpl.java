package com.xxx.dao.WorkTitle;

import com.xxx.dao.Position.PositionDao;
import com.xxx.pojo.Position;
import com.xxx.pojo.WorkTitle;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WorkTitleDaoImpl implements WorkTitleDao{
    @Override
    public int deleteByPrimaryKey(String workTitleId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        int i = 0;
        try {
            i = workTitleDao.deleteByPrimaryKey(workTitleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(WorkTitle record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        int i = 0;
        try {
            i = workTitleDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insertSelective(WorkTitle record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        int i = 0;
        try {
            i = workTitleDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public WorkTitle selectByPrimaryKey(String workTitleId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        WorkTitle workTitle = null;
        try {
            workTitle = workTitleDao.selectByPrimaryKey(workTitleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return workTitle;
    }

    @Override
    public List<WorkTitle> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        List<WorkTitle> workTitle = null;
        try {
            workTitle = workTitleDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return workTitle;
    }

    @Override
    public int updateByPrimaryKeySelective(WorkTitle record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        int i = 0;
        try {
            i = workTitleDao.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(WorkTitle record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        int i = 0;
        try {
            i = workTitleDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public WorkTitle selectAllByWorkTitleName(String workTitleName){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        WorkTitleDao workTitleDao = sqlSession.getMapper(WorkTitleDao.class);
        WorkTitle workTitle = null;
        try {
            workTitle = workTitleDao.selectAllByWorkTitleName(workTitleName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return workTitle;
    }
}
