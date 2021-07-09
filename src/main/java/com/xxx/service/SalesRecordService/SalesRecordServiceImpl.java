package com.xxx.service.SalesRecordService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.*;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.service.SaleTaskService.SaleTaskService;
import com.xxx.service.SaleTaskService.SaleTaskServiceImpl;

import java.util.List;

public class SalesRecordServiceImpl implements SalesRecordService {
    private SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();

    @Override
    public List<SaleRecord> getPersonalSalesRecord(String userId) {
        return saleRecordDao.selectAllByUserId(userId);
    }

    @Override
    public boolean insertOneSaleRecord(SaleRecord saleRecord) {
        SaleTaskService service = new SaleTaskServiceImpl();
        SaleTask saleTask = service.getBySaleTaskId(saleRecord.getSaleTaskId());
        ProductWarehouseService warehouseService = new ProductWarehouseServiceImpl();
        ProductWarehouse productWarehouse = warehouseService.getByProductWarehouseId(saleTask.getProductWarehouseId());
        if (saleTask.getProductNumber() - saleRecord.getSaleNumber() >= 0) {
            int num = saleTask.getProductNumber();
            saleTask.setProductNumber(saleTask.getProductNumber() - saleRecord.getSaleNumber());
            if (num - saleRecord.getSaleNumber() == 0) {
                saleTask.setFinishState("已完成");
            }
            if (service.updateProductNum(saleTask)) {
                if (saleRecordDao.insertSelective(saleRecord) > 0) {
                    productWarehouse.setProductNumber(productWarehouse.getProductNumber()-saleRecord.getSaleNumber());
                    return warehouseService.updateProductNumber(productWarehouse);
                }
                else
                    return false;
            } else {
                return false;
            }
        } else return false;
    }

    @Override
    public List<SaleRecord> getAllSalesRecord() {
        return saleRecordDao.selectAll();
    }

    @Override
    public JSONArray toJSONArray(List<SaleRecord> saleRecords) {
        JSONArray jsonArray = new JSONArray();
        UserDataDao userDataDao = new UserDataDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        for (SaleRecord s : saleRecords) {
            JSONObject object = new JSONObject();
            object.put("saleRecordId", s.getSaleRecordId());
            object.put("userId", s.getUserId());
            UserData userData = userDataDao.selectByPrimaryKey(s.getUserId());
            object.put("userName", userData.getUserName());
            object.put("saleTaskId", s.getSaleTaskId());
            object.put("productId", s.getProductId());
            Product product = productDao.selectByPrimaryKey(s.getProductId());
            object.put("productName", product.getProductName());
            object.put("saleNumber", s.getSaleNumber());
            object.put("saleOneMoney", s.getSaleOneMoney());
            object.put("saleSumMoney", s.getSaleSumMoney());
            object.put("saleFinishTime", s.getSaleFinishTime());
            object.put("saleRecordState", s.getSaleRecordState());
            jsonArray.add(object);
        }
        return jsonArray;
    }

    @Override
    public boolean deleteOneSaleRecord(String saleRecordId) {
        return saleRecordDao.deleteByPrimaryKey(saleRecordId)>0;
    }

    @Override
    public boolean updateOneSaleRecord(SaleRecord saleRecord) {
        return saleRecordDao.updateByPrimaryKeySelective(saleRecord)>0;
    }

    @Override
    public List<SaleRecord> getSaleRecordByUserAndTime(String userId, String startTime, String endTime) {
        return saleRecordDao.selectAllByUserIdAndTime(userId, startTime, endTime);
    }
}
