package com.xxx.service.SalesRecordService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.SaleRecord;
import com.xxx.pojo.UserData;

import java.util.List;

public class SalesRecordServiceImpl implements SalesRecordService{
    private SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();
    @Override
    public List<SaleRecord> getPersonalSalesRecord(String userId) {
        return saleRecordDao.selectAllByUserId(userId);
    }

    @Override
    public int insertOneSaleRecord(SaleRecord saleRecord) {
        return saleRecordDao.insertSelective(saleRecord);
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
        for (SaleRecord s: saleRecords) {
            JSONObject object = new JSONObject();
            object.put("saleRecordId",s.getSaleRecordId());
            object.put("userId",s.getUserId());
            UserData userData = userDataDao.selectByPrimaryKey(s.getUserId());
            object.put("userName",userData.getUserName());
            object.put("saleTaskId",s.getSaleTaskId());
            object.put("productId",s.getProductId());
            Product product = productDao.selectByPrimaryKey(s.getProductId());
            object.put("productName",product.getProductName());
            object.put("saleNumber",s.getSaleNumber());
            object.put("saleOneMoney",s.getSaleOneMoney());
            object.put("saleSumMoney",s.getSaleSumMoney());
            object.put("saleFinishTime",s.getSaleFinishTime());
            object.put("saleRecordState",s.getSaleRecordState());
            jsonArray.add(object);
        }
        return jsonArray;
    }
}
