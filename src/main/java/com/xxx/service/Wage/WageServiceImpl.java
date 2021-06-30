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

    @Override
    public double getUserMonthWage(String userId, String timeMonthSearch) {
        Wage wageByUserIdAndMonth = wageDao.getWageByUserIdAndMonth(userId, timeMonthSearch);
        if (null == wageByUserIdAndMonth)
            return 0;
        // 保留两位小数
        return Double.parseDouble(String.format("%.2f",wageByUserIdAndMonth.getRealyWage()));
    }

    @Override
    public double getUserYearWage(String userId, String timeYearSearch) {

        double sum = 0;
        for (int i = 1; i <= 12; i++) {
            String timeMonthSearch;
            if (i < 10) {
                timeMonthSearch = timeYearSearch + "-0" + i;
            } else {
                timeMonthSearch = timeYearSearch + "-" + i;
            }

            sum += getUserMonthWage(userId, timeMonthSearch);
        }
        return Double.parseDouble(String.format("%.2f",sum));
    }

    @Override
    public double getDepartmentYearWage(String departmentId, String timeYearSearch) {
        List<Wage> departmentYearWageList = wageDao.getDepartmentYearWageList(departmentId, timeYearSearch);

        double sum = 0;
        for (Wage wage : departmentYearWageList) {
            sum += wage.getRealyWage();
        }
        return Double.parseDouble(String.format("%.2f",sum));
    }
}
