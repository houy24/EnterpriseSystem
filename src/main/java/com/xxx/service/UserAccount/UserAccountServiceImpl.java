package com.xxx.service.UserAccount;

import com.xxx.dao.UserAccount.UserAccountMapper;
import com.xxx.dao.UserAccount.UserAccountMapperImpl;
import com.xxx.pojo.UserAccount;

import java.util.List;

public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountMapper userAccountMapper = null;

    public UserAccountServiceImpl() {
        userAccountMapper = new UserAccountMapperImpl();
    }


    @Override
    public List<UserAccount> getAllUserAccount() {
        return userAccountMapper.getAllUserAccount();
    }

    @Override
    public UserAccount getUserAccountByUserAccountId(String userId) {
        return userAccountMapper.getUserAccountByUserAccountId(userId);
    }

    @Override
    public UserAccount getUserAccountByUserPhone(String userPhone) {
        return userAccountMapper.getUserAccountByUserPhone(userPhone);
    }

    @Override
    public boolean addUserAccount(UserAccount userAccount) {
        int res = userAccountMapper.addUserAccount(userAccount);
        return res > 0;
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        int res = userAccountMapper.updateUserAccount(userAccount);
        return res > 0;
    }

    @Override
    public boolean deleteUserAccountByUserId(String userId) {
        int res = userAccountMapper.deleteUserAccountByUserId(userId);
        return res > 0;
    }

    @Override
    public UserAccount login(String userPhone, String userPassword) {
        UserAccount userAccount = userAccountMapper.getUserAccountByUserPhone(userPhone);
        // 获取登录的员工
        if (null != userAccount) {
            System.out.println("当前登录用户为：" + userAccount); // 测试输出
        } else {
            System.out.println("当前登录用户为空：" + "null"); //测试输出
        }
        if (null != userAccount) {
            if (!userAccount.getUserPassword().equals(userPassword)) {
                System.out.println("密码错误！");
                userAccount = null;    //密码不对，设置用户为空
            }
        }
        return userAccount; //返回员工
    }

    @Override
    public void updateUserPasswordById(String userid,String userPassword){
        userAccountMapper.updateUserPasswordById(userid, userPassword);
    }
}
