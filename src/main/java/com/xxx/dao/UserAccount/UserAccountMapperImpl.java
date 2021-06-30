package com.xxx.dao.UserAccount;

import com.xxx.pojo.UserAccount;
import com.xxx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserAccountMapperImpl implements UserAccountMapper {

    @Override
    public List<UserAccount> getAllUserAccount() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        List<UserAccount> userAccountList = null;
        try {
            userAccountList = userAccountMapper.getAllUserAccount();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userAccountList;
    }

    @Override
    public UserAccount getUserAccountByUserAccountId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        UserAccount userAccount = null;
        try {
            userAccount = userAccountMapper.getUserAccountByUserAccountId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userAccount;
    }

    @Override
    public UserAccount getUserAccountByUserPhone(String userPhone) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        UserAccount userAccount = null;
        try {
            userAccount = userAccountMapper.getUserAccountByUserPhone(userPhone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userAccount;
    }

    @Override
    public int addUserAccount(UserAccount userAccount) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        int res = 0;
        try {
            res = userAccountMapper.addUserAccount(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    @Override
    public int updateUserAccount(UserAccount userAccount) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        int res = 0;
        try {
            res = userAccountMapper.updateUserAccount(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }

    @Override
    public int deleteUserAccountByUserId(String userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserAccountMapper userAccountMapper = sqlSession.getMapper(UserAccountMapper.class);

        int res = 0;
        try {
            res = userAccountMapper.deleteUserAccountByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return res;
    }
}
