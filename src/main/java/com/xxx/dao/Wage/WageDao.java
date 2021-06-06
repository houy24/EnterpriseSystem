package com.xxx.dao.Wage;

import com.xxx.pojo.Wage;

import java.util.List;

public interface WageDao {
    int deleteByPrimaryKey(String wageId);

    int insert(Wage record);

    int insertSelective(Wage record);

    Wage selectByPrimaryKey(String wageId);

    List<Wage> selectAllByUserId(String userId);

    int updateByPrimaryKeySelective(Wage record);

    int updateByPrimaryKey(Wage record);
}