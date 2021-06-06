package com.xxx.dao.WageContent;

import com.xxx.pojo.WageContent;

import java.util.List;

public interface WageContentDao {
    int deleteByPrimaryKey(String wageId);

    int insert(WageContent record);

    int insertSelective(WageContent record);

    WageContent selectByPrimaryKey(String wageId);

    List<WageContent> selectAllByUserId(String userId);

    List<WageContent> selectAll();

    int updateByPrimaryKeySelective(WageContent record);

    int updateByPrimaryKey(WageContent record);
}