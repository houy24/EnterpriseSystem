package com.xxx.service.PersonalSaleRecord;

import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.pojo.SaleRecord;

import java.util.Date;
import java.util.List;

public class SaleRecordServiceImpl implements SaleRecordService{

    private SaleRecordDao saleRecordDao = null;

    public SaleRecordServiceImpl() {
        saleRecordDao = new SaleRecordDaoImpl();
    }
    @Override
    public int selectCountByUserId(String userId) {
        return saleRecordDao.selectCountByUserId(userId);
    }

    @Override
    public List<SaleRecord> selectAllByUserId(String userId){
        return saleRecordDao.selectAllByUserId(userId);
    }
    @Override
    public List<SaleRecord> selectAllByMaxNumber(String userId,String Maxnumber){
        return saleRecordDao.selectAllByMaxNumber(userId, Maxnumber);
    }
    @Override
    public List<SaleRecord> selectAllByDate(String userId, String saleFinishTime){
        return saleRecordDao.selectAllByDate(userId, saleFinishTime);
    }
}
