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
}