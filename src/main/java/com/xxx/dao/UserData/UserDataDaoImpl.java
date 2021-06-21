package com.xxx.dao.UserData;

import com.xxx.pojo.UserData;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class UserDataDaoImpl implements UserDataDao {
    /**
     *
     * @param userid
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String userid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        System.out.println("UserDataDaoImpl => deleteByPrimaryKey");
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

    /**
     *
     * @param record
     * @return
     */
    @Override
    public int insert(UserData record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        System.out.println("UserDataDaoImpl => insert");
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
        System.out.println("UserDataDaoImpl => selectAll");
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

    /**
     *
     * @param userid
     * @return
     */
    @Override
    public UserData selectByPrimaryKey(String userid) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        System.out.println("UserDataDaoImpl => selectByPrimaryKey");
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

    /**
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(UserData record) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);
        System.out.println("UserDataDaoImpl => updateByPrimaryKey");
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

    /* 新增 ，根据职称id查询有几个用户，有该职称 */
    @Override
    public int selectCountByWorkTitleId(String workTitleId) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        System.out.println("UserDataDaoImpl => selectCountByWorkTitleId");
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

    /* 新增 ，根据用户手机号获取用户信息 */
    @Override
    public UserData selectByUserPhone(String userPhone) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        System.out.println("UserDataDaoImpl => selectByuserPhone");
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

    /* 新增 , 根据岗位id查询有几个用户，有该岗位 */
    @Override
    public int selectCountByPositionId(String positionId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDataDao userDataDao = sqlSession.getMapper(UserDataDao.class);

        System.out.println("UserDataDaoImpl => selectCountByPositionId");
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
}
