package com.xxx.dao.WorkAgeMoney;

import com.xxx.pojo.WorkAgeMoney;

import java.util.List;

public interface WorkAgeMoneyDao {
    int deleteByPrimaryKey(int workAge);

    int insert(WorkAgeMoney record);

    int insertSelective(WorkAgeMoney record);

    WorkAgeMoney selectByPrimaryKey(int workAge);

    List<WorkAgeMoney> selectAll();

    int updateByPrimaryKeySelective(WorkAgeMoney record);

    int updateByPrimaryKey(WorkAgeMoney record);
}