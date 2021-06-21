package com.xxx.service.UserData;

import com.xxx.pojo.UserData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDataService {

    /* 新增 ，根据用户手机号获取用户信息 */
    UserData selectByUserPhone(String userPhone);

    // 该职称如果仍然有人拥有，则不可删除
    boolean judgeWorkTitleExists(String workTitleId);

    // 该岗位如果有人拥有，则不可删除
    boolean judgePositionExists(String positionId);

    UserData selectByUserId(String userId);

    List<UserData> selectAll();

}
