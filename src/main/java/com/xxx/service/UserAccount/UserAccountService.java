package com.xxx.service.UserAccount;

import com.xxx.pojo.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountService {

    // 获取所有账号
    List<UserAccount> getAllUserAccount();

    // 根据用户编号获取用户
    UserAccount getUserAccountByUserAccountId(String userId);

    // 根据用户手机号获取用户
    UserAccount getUserAccountByUserPhone(String userPhone);

    // 增加用户
    boolean addUserAccount(UserAccount userAccount);

    // 修改用户账号
    boolean updateUserAccount(UserAccount userAccount);

    // 删除用户账号根据用户id
    boolean deleteUserAccountByUserId(String userId);

    // 用户登录     service层调用dao层
    UserAccount login(String userPhone,String userPassword);

}
