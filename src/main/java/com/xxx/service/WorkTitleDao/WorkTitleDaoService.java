package com.xxx.service.WorkTitleDao;

import com.xxx.pojo.WorkTitle;

import java.util.List;

public interface WorkTitleDaoService {
    //查询所有职称
    List<WorkTitle> selectAll();
    //根据职称名称查询信息
    WorkTitle selectAllByWorkTitleName(String workTitleName);
}
