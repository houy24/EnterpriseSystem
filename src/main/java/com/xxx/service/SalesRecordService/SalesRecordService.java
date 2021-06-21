package com.xxx.service.SalesRecordService;

import com.alibaba.fastjson.JSONArray;
import com.xxx.pojo.SaleRecord;

import java.util.List;

public interface SalesRecordService {
    List<SaleRecord> getPersonalSalesRecord(String userId);
    boolean insertOneSaleRecord(SaleRecord saleRecord);
    List<SaleRecord> getAllSalesRecord();
    JSONArray toJSONArray(List<SaleRecord> saleRecords);
    boolean deleteOneSaleRecord(String saleRecordId);
    boolean updateOneSaleRecord(SaleRecord saleRecord);
}
