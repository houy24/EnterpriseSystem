package com.xxx.dao.TaxRate;

import com.xxx.pojo.TaxRate;

import java.util.List;

public interface TaxRateDao {
    int deleteByPrimaryKey(String taxRateId);

    int insert(TaxRate record);

    int insertSelective(TaxRate record);

    TaxRate selectByPrimaryKey(String taxRateId);

    List<TaxRate> selectAll();

    int updateByPrimaryKeySelective(TaxRate record);

    int updateByPrimaryKey(TaxRate record);
}