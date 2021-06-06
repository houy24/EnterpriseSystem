package com.xxx.dao.Position;

import com.xxx.pojo.Position;

import java.util.List;

public interface PositionDao {
    int deleteByPrimaryKey(String positionId);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(String positionId);

    List<Position> selectAllByPositionDesrciption(String positionDesrciption);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}