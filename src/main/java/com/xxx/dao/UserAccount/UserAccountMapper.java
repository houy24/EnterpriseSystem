package com.xxx.dao.UserAccount;

import com.xxx.pojo.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountMapper {

    // 获取所有账号
    List<UserAccount> getAllUserAccount();

    // 根据用户编号获取用户
    UserAccount getUserAccountByUserAccountId(@Param("userId") String userId);

    // 根据用户手机号获取用户
    UserAccount getUserAccountByUserPhone(@Param("userPhone") String userPhone);

    // 增加用户
    int addUserAccount(UserAccount userAccount);

    // 修改用户账号
    int updateUserAccount(UserAccount userAccount);

    // 删除用户账号根据用户id
    int deleteUserAccountByUserId(@Param("userId") String userId);


}
