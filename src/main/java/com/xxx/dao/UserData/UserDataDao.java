package com.xxx.dao.UserData;

import com.xxx.pojo.UserData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDataDao {
    List<UserData> selectAll();

    UserData selectByPrimaryKey(@Param("userid") String userid);

    int deleteByPrimaryKey(@Param("userid") String userid);

    int insert(@Param("record") UserData record);

    int updateByPrimaryKey(@Param("userid") UserData record);

    /* 新增 ，根据职称id查询有几个用户，有该职称 */
    int selectCountByWorkTitleId(@Param("workTitleId") String workTitleId);


    /* 新增 ，根据用户手机号获取用户信息 */
    UserData selectByUserPhone(@Param("userPhone") String userPhone);

    /* 新增 , 根据岗位id查询有几个用户，有该岗位 */
    int selectCountByPositionId(@Param("positionId") String positionId);
}