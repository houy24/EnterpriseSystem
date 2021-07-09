package com.xxx.service.SaleRecord;

import com.xxx.pojo.SaleRecord;

import java.util.List;

public interface SaleRecordService {

    // 获取所有销售记录
    List<SaleRecord> getAllSaleRecord();

    // 根据销售记录编号获取销售记录
    SaleRecord getSaleRecordById(String saleRecordId);

    // 获取某个用户的销售记录
    List<SaleRecord> getSaleRecordByUserId(String userId);

    // 获取某个月份的销售记录
    List<SaleRecord> getSaleRecordByMonth(String timeMonthSearch);

    // 获取某个用户在某个月份的销售记录
    List<SaleRecord> getSaleRecordByUserIdAndMonth(String userId, String timeMonthSearch);

    List<SaleRecord> getSaleRecordByUserAndTime(String userId, String startTime, String endTime);

}
