package com.xxx.service.SaleRecord;

import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.pojo.SaleRecord;
import com.xxx.utils.MyDateTimeUtils;

import java.util.List;

public class SaleRecordServiceImpl implements SaleRecordService {

    private SaleRecordDao saleRecordDao = null;

    public SaleRecordServiceImpl() {
        saleRecordDao = new SaleRecordDaoImpl();
    }

    @Override
    public List<SaleRecord> getAllSaleRecord() {
        return saleRecordDao.selectAll();
    }

    @Override
    public SaleRecord getSaleRecordById(String saleRecordId) {
        return saleRecordDao.selectByPrimaryKey(saleRecordId);
    }

    @Override
    public List<SaleRecord> getSaleRecordByUserId(String userId) {
        return saleRecordDao.selectAllByUserId(userId);
    }

    @Override
    public List<SaleRecord> getSaleRecordByMonth(String timeMonthSearch) {
        List<SaleRecord> saleRecordList = saleRecordDao.selectAll();

        for (int i = 0; i < saleRecordList.size(); i++) {
            String nowTime = MyDateTimeUtils.getTimeByDate(saleRecordList.get(i).getSaleFinishTime());
            if (!nowTime.startsWith(timeMonthSearch)) {
                saleRecordList.remove(i);
                i--;
            }
        }

        return saleRecordList;
    }

    @Override
    public List<SaleRecord> getSaleRecordByUserIdAndMonth(String userId, String timeMonthSearch) {
        List<SaleRecord> saleRecordList = saleRecordDao.selectAllByUserId(userId);

        for (int i = 0; i < saleRecordList.size(); i++) {
            String nowTime = MyDateTimeUtils.getTimeByDate(saleRecordList.get(i).getSaleFinishTime());
            if (!nowTime.startsWith(timeMonthSearch)) {
                saleRecordList.remove(i);
                i--;
            }
        }

        return saleRecordList;
    }
}
