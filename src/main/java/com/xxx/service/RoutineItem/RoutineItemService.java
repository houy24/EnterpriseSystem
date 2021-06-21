package com.xxx.service.RoutineItem;

import com.xxx.pojo.RoutineItem;

public interface RoutineItemService {

    RoutineItem getRoutineItemById(String routineItemId);

    boolean updateRoutineItem(RoutineItem routineItem);

}
