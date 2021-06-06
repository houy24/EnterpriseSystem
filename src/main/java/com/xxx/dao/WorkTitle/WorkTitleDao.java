package com.xxx.dao.WorkTitle;

import com.xxx.pojo.WorkTitle;

import java.util.List;

public interface WorkTitleDao {
    int deleteByPrimaryKey(String workTitleId);

    int insert(WorkTitle record);

    int insertSelective(WorkTitle record);

    WorkTitle selectByPrimaryKey(String workTitleId);

    List<WorkTitle> selectAll();

    int updateByPrimaryKeySelective(WorkTitle record);

    int updateByPrimaryKey(WorkTitle record);
}