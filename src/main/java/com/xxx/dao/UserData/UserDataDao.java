package com.xxx.dao.UserData;

import com.xxx.pojo.UserData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDataDao {
    List<UserData> selectAll();

    UserData selectByPrimaryKey(String userId);

    int deleteByPrimaryKey( String userId);

    int insert(UserData record);

    int updateByPrimaryKey(UserData record);

    /* 新增 ，根据职称id查询有几个用户，有该职称 */
    int selectCountByWorkTitleId(String workTitleId);


    /* 新增 ，根据用户手机号获取用户信息 */
    UserData selectByUserPhone(String userPhone);

    /* 新增 , 根据岗位id查询有几个用户，有该岗位 */
    int selectCountByPositionId( String positionId);

    //更新照片
    void updateByUserId(@Param("userId") String userId,@Param("ImgPath") String ImgPath);
    //通过名字进行模糊查询
    List<UserData> selectAllByUserName(String userName);
    //通过部门Id查询人员
    List<UserData> selectAllByDepartmentId(String departmentId);
}