package com.xxx.service.UserData;

import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.UserData;

import java.util.List;

public class UserDataServiceImpl implements UserDataService {

    private UserDataDao userDataDao = null;

    public UserDataServiceImpl() {
        userDataDao = new UserDataDaoImpl();
    }


    @Override
    public UserData selectByUserPhone(String userPhone) {
        return userDataDao.selectByUserPhone(userPhone);
    }

    @Override
    public boolean judgeWorkTitleExists(String workTitleId) {
        int res = userDataDao.selectCountByWorkTitleId(workTitleId);
        return res > 0;
    }

    @Override
    public boolean judgePositionExists(String positionId) {
        int res = userDataDao.selectCountByPositionId(positionId);
        return res > 0;
    }

    @Override
    public UserData selectByUserId(String userId) {
        return userDataDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<UserData> selectAll() {
        return userDataDao.selectAll();
    }


    @Override
    public void updateByUserId(String userid,String ImgPath){
        userDataDao.updateByUserId(userid,ImgPath);
    }

    @Override
    public List<UserData> selectAllByUserName(String userName){
        return userDataDao.selectAllByUserName(userName);
    }

    @Override
    public List<UserData> selectAllByDepartmentId(String departmentId){
        return  userDataDao.selectAllByDepartmentId(departmentId);
    }
    @Override
    public int insert(UserData record){
        return userDataDao.insert(record);
    }
    @Override
    public int updateByPrimaryKey(UserData record){
        return userDataDao.updateByPrimaryKey(record);
    }
    @Override
    public int deleteByPrimaryKey(String userid){
        return userDataDao.deleteByPrimaryKey(userid);
    }
}
