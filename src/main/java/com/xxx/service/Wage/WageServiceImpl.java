package com.xxx.service.Wage;

import com.xxx.dao.Wage.WageDao;
import com.xxx.dao.Wage.WageDaoImpl;
import com.xxx.pojo.Wage;

import java.util.List;

public class WageServiceImpl implements WageService {

    private WageDao wageDao = null;

    public WageServiceImpl() {
        wageDao = new WageDaoImpl();
    }

    @Override
    public List<Wage> getAllWage() {
        return wageDao.selectAll();
    }

    @Override
    public Wage getWageById(String wageId) {
        return wageDao.selectByPrimaryKey(wageId);
    }

    @Override
    public List<Wage> getWageByuserId(String userId) {
        return wageDao.selectAllByUserId(userId);
    }

    @Override
    public List<Wage> getWageByMonth(String timeMonthSearch) {
        return wageDao.getWageByMonth(timeMonthSearch);
    }

    @Override
    public Wage getWageByUserIdAndMonth(String userId, String timeMonthSearch) {
        return wageDao.getWageByUserIdAndMonth(userId, timeMonthSearch);
    }

    @Override
    public boolean judgeExistWageByMonth(String timeMonthSearch) {
        int res = wageDao.getCountByMonth(timeMonthSearch);
        return res > 0;
    }
}
