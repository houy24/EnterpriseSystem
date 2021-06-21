package com.xxx.service.Position;

import com.xxx.pojo.Position;
import com.xxx.pojo.WorkTitle;

import java.util.List;

public interface PositionService {

    List<Position> getAllPosition();

    Position getPositionById(String positionId);

    boolean updatePosition(Position position);

    boolean deletePosition(String positionId);

    // 有主键
    boolean insertPosition(Position position);

    // 无主键，此处生成 UUID
    boolean insertPositionNoPrimaryKey(Position position);

    // 该职称如果仍然有人拥有，则不可删除
    boolean judgePositionExists(String positionId);
}
