package com.xxx.service.WorkAgeMoney;

import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDao;
import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDaoImpl;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import java.util.List;

public class WorkAgeMoneyServiceImpl implements WorkAgeMoneyService {

    private WorkAgeMoneyDao workAgeMoneyDao = null;

    public WorkAgeMoneyServiceImpl() {
        workAgeMoneyDao = new WorkAgeMoneyDaoImpl();
    }

    @Override
    public List<WorkAgeMoney> getAllWorkAgeMoney() {
        return workAgeMoneyDao.selectAll();
    }

    @Override
    public WorkAgeMoney getWorkAgeMoneyByWorkAge(int workAge) {
        return workAgeMoneyDao.selectByPrimaryKey(workAge);
    }

    @Override
    public boolean insertWorkAgeMoney(WorkAgeMoney workAgeMoney) {
        int res = workAgeMoneyDao.insert(workAgeMoney);
        return res > 0;
    }

    @Override
    public boolean deleteWorkAgeMoney(int workAge) {
        int res = workAgeMoneyDao.deleteByPrimaryKey(workAge);
        return res > 0;
    }

    @Override
    public boolean updateWorkAgeMoney(WorkAgeMoney workAgeMoney,int preWorkAge) {
        // update = delete + add（不重复）
        int res = 0;

        if (preWorkAge == workAgeMoney.getWorkAge()) { // 主键不变，直接更新
            res = workAgeMoneyDao.updateByPrimaryKey(workAgeMoney);
        } else {
            // 主键不同，查询改变后的主键是否有冲突
            WorkAgeMoney workAgeMoneyAfter = workAgeMoneyDao.selectByPrimaryKey(workAgeMoney.getWorkAge());

            if (null == workAgeMoneyAfter) { // 空，不冲突，可以执行

                int res1 = workAgeMoneyDao.deleteByPrimaryKey(preWorkAge); // 删掉旧的
                if (res1 > 0) { // 删除成功
                    res = workAgeMoneyDao.insert(workAgeMoney); // 增加新的
                }
            } else { // 冲突，不能改主键，主键已经存在

            }
        }

        return res > 0;
    }


}
