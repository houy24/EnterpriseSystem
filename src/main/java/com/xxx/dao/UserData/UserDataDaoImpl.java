package com.xxx.dao.UserData;

import com.xxx.pojo.UserData;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class UserDataDaoImpl implements UserDataDao {
    @Override
    public int deleteByPrimaryKey(String userid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        int i=0;
        try {
            i = userDataDao.deleteByPrimaryKey(userid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public int insert(UserData record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        int i=0;
        try {
            i = userDataDao.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return i;
    }

    @Override
    public List<UserData> selectAll() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        List<UserData> userDataList = null;
        try {
            userDataList = userDataDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return userDataList;
    }

    @Override
    public UserData selectByPrimaryKey(String userid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        UserData userData = null;
        try {
            userData = userDataDao.selectByPrimaryKey(userid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userData;
    }

    @Override
    public int updateByPrimaryKey(UserData record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        int i=0;
        try {
            i = userDataDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return i;
    }

    /* ?????? ???????????????id???????????????????????????????????? */
    @Override
    public int selectCountByWorkTitleId(String workTitleId) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        int res = 0;
        try {
            res = userDataDao.selectCountByWorkTitleId(workTitleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    /* ?????? ?????????????????????????????????????????? */
    @Override
    public UserData selectByUserPhone(String userPhone) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        UserData userData = null;
        try {
            userData = userDataDao.selectByUserPhone(userPhone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userData;
    }

    /* ?????? , ????????????id???????????????????????????????????? */
    @Override
    public int selectCountByPositionId(String positionId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        int count = 0;
        try {
            count = userDataDao.selectCountByPositionId(positionId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return count;
    }
    @Override
    public void updateByUserId(String userid,String ImgPath) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);


        try {
            userDataDao.updateByUserId(userid,ImgPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<UserData> selectAllByUserName(String userName) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        List<UserData> userData = null;
        try {
            userData = userDataDao.selectAllByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return userData;
    }

    @Override
    public List<UserData> selectAllByDepartmentId(String departmentId) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        List<UserData> userDataList = null;
        try {
            userDataList = userDataDao.selectAllByDepartmentId(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return userDataList;
    }
}
