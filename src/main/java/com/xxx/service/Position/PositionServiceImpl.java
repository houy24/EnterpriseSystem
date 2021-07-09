package com.xxx.service.Position;

import com.xxx.dao.Position.PositionDao;
import com.xxx.dao.Position.PositionDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.Position;
import com.xxx.pojo.UserData;
import com.xxx.utils.UUIDUtils;

import java.util.List;

public class PositionServiceImpl implements PositionService {

    private PositionDao positionDao = null;
    private UserDataDao userDataDao = null;

    public PositionServiceImpl() {
        positionDao = new PositionDaoImpl();
        userDataDao = new UserDataDaoImpl();
    }

    @Override
    public List<Position> getAllPosition() {
        return positionDao.selectAll();
    }

    @Override
    public Position getPositionById(String positionId) {
        return positionDao.selectByPrimaryKey(positionId);
    }

    @Override
    public boolean updatePosition(Position position) {
        int res = positionDao.updateByPrimaryKey(position);
        return res > 0;
    }

    @Override
    public boolean deletePosition(String positionId) {

        // 再校验，判断该岗位是否还存在用户表
        if (judgePositionExists(positionId)) {
            return false; // 删除失败
        }

        int res = positionDao.deleteByPrimaryKey(positionId);
        return res > 0;
    }

    @Override
    public boolean insertPosition(Position position) {
        int res = positionDao.insert(position);
        return res > 0;
    }

    @Override
    public boolean insertPositionNoPrimaryKey(Position position) {
        String positionId = UUIDUtils.getUUIDArg("position-");
        System.out.println("positionId => " + positionId);
        position.setPositionId(positionId);
        int res = positionDao.insert(position);
        return res > 0;
    }

    @Override
    public boolean judgePositionExists(String positionId) { // 判断该岗位是否有人使用
        int res = userDataDao.selectCountByPositionId(positionId);
        return res > 0;
    }

    @Override
    public Position selectAllByPositionName(String positionName){
        return positionDao.selectAllByPositionName(positionName);
    }
}
