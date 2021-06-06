package com.xxx.dao.SaleTask;

import com.xxx.pojo.SaleTask;

import java.util.List;

public interface SaleTaskDao {
    int deleteByPrimaryKey(String saleTaskId);

    int insert(SaleTask record);

    int insertSelective(SaleTask record);

    SaleTask selectByPrimaryKey(String saleTaskId);

    List<SaleTask> selectAllByUserId(String userId);

    int updateByPrimaryKeySelective(SaleTask record);

    int updateByPrimaryKey(SaleTask record);
}