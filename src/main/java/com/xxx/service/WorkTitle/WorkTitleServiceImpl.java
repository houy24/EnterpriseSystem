package com.xxx.service.WorkTitle;

import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.dao.WorkTitle.WorkTitleDao;
import com.xxx.dao.WorkTitle.WorkTitleDaoImpl;
import com.xxx.pojo.WorkTitle;
import com.xxx.utils.UUIDUtils;

import java.util.List;

public class WorkTitleServiceImpl implements WorkTitleService {

    private WorkTitleDao workTitleDao = null;
    UserDataDao userDataDao = null;

    public WorkTitleServiceImpl() {
        workTitleDao = new WorkTitleDaoImpl();
        userDataDao = new UserDataDaoImpl();
    }

    @Override
    public List<WorkTitle> getAllWorkTitle() {
        return workTitleDao.selectAll();
    }

    @Override
    public WorkTitle getWorkTitleById(String workTitleId) {
        return workTitleDao.selectByPrimaryKey(workTitleId);
    }

    @Override
    public boolean updateWorkTitle(WorkTitle workTitle) {
        int res = workTitleDao.updateByPrimaryKey(workTitle);
        return res > 0;
    }


    @Override
    public boolean deleteWorkTitle(String workTitleId) {
        // 再校验，判断该职称是否还存在用户表
        if (judgeWorkTitleExists(workTitleId)) {
            return false; // 删除失败
        }

        int res = workTitleDao.deleteByPrimaryKey(workTitleId);
        return res > 0;
    }

    @Override
    public boolean insertWorkTitle(WorkTitle workTitle) {
        int res = workTitleDao.insert(workTitle);
        return res > 0;
    }

    @Override
    public boolean insertWorkTitleNoPrimaryKey(WorkTitle workTitle) {
        String workTitleId = UUIDUtils.getUUIDArg("title-");

        System.out.println("workTitleId => " + workTitleId);
        workTitle.setWorkTitleId(workTitleId);

        int res = workTitleDao.insert(workTitle);
        return res > 0;
    }

    @Override
    public boolean judgeWorkTitleExists(String workTitleId) {  // 该职称如果仍然有人拥有，则不可删除
        // 用户信息表
        int res = userDataDao.selectCountByWorkTitleId(workTitleId);
        return res > 0;
    }
}
