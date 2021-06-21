package com.xxx.service.RoutineItem;

import com.xxx.dao.RoutineItem.RoutineItemDao;
import com.xxx.dao.RoutineItem.RoutineItemDaoImpl;
import com.xxx.pojo.RoutineItem;

public class RoutineItemServiceImpl implements RoutineItemService {

    private RoutineItemDao routineItemDao = null;

    public RoutineItemServiceImpl() {
        routineItemDao = new RoutineItemDaoImpl();
    }

    @Override
    public RoutineItem getRoutineItemById(String routineItemId) {
        return routineItemDao.selectByPrimaryKey(routineItemId);
    }

    @Override
    public boolean updateRoutineItem(RoutineItem routineItem) {
        int res = routineItemDao.updateByPrimaryKey(routineItem);
        return res > 0;
    }
}
