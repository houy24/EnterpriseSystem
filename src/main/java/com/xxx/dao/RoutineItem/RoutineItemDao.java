package com.xxx.dao.RoutineItem;

import com.xxx.pojo.RoutineItem;

public interface RoutineItemDao {
    int deleteByPrimaryKey(String routineItemId);

    int insert(RoutineItem record);

    int insertSelective(RoutineItem record);

    RoutineItem selectByPrimaryKey(String routineItemId);

    int updateByPrimaryKeySelective(RoutineItem record);

    int updateByPrimaryKey(RoutineItem record);
}