package com.xxx.service.WorkTitleDao;

import com.xxx.dao.WorkTitle.WorkTitleDao;
import com.xxx.dao.WorkTitle.WorkTitleDaoImpl;
import com.xxx.pojo.WorkTitle;

import java.util.List;

public class WorkTitleDaoServiceImpl implements WorkTitleDaoService{
    private WorkTitleDao workTitleDao = null;

    public WorkTitleDaoServiceImpl(){
        workTitleDao = new WorkTitleDaoImpl();
    }

    @Override
    public List<WorkTitle> selectAll() {
        return workTitleDao.selectAll();
    }
    @Override
    public WorkTitle selectAllByWorkTitleName(String workTitleName){
        return workTitleDao.selectAllByWorkTitleName(workTitleName);
    }
}
