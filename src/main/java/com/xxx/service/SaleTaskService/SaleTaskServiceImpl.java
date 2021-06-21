package com.xxx.service.SaleTaskService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.dao.SaleTask.SaleTaskDao;
import com.xxx.dao.SaleTask.SaleTaskDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;
import com.xxx.pojo.SaleTask;
import com.xxx.pojo.UserData;

import java.util.List;

public class SaleTaskServiceImpl implements SaleTaskService {
    private SaleTaskDao saleTaskDao = new SaleTaskDaoImpl();

    @Override
    public List<SaleTask> getAllSaleTasks() {
        return saleTaskDao.selectAll();
    }

    @Override
    public boolean insertOneSaleTask(SaleTask saleTask) {
        int i = saleTaskDao.insertSelective(saleTask);
        return i > 0;
    }

    @Override
    public List<SaleTask> getSaleTaskByUserId(String userId) {
        return saleTaskDao.selectAllByUserId(userId);
    }

    @Override
    public JSONArray toJSONArray(List<SaleTask> saleTaskList) {
        JSONArray array = new JSONArray();
        UserDataDao userDataDao = new UserDataDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
        for (SaleTask s :
                saleTaskList) {
            JSONObject object = new JSONObject();
            object.put("saleTaskId", s.getSaleTaskId());
            UserData userData = userDataDao.selectByPrimaryKey(s.getUserId());
            object.put("userId", s.getUserId());
            object.put("userName", userData.getUserName());
            Product product = productDao.selectByPrimaryKey(s.getProductId());
            object.put("productId", product.getProductId());
            object.put("productName", product.getProductName());
            ProductType productType = productTypeDao.selectByPrimaryKey(product.getProductTypeId());
            object.put("productTypeId", productType.getProductTypeId());
            object.put("productTypeName", productType.getProductTypeName());
            object.put("productCommission", productType.getProductCommission());
            object.put("productWarehouseId", s.getProductWarehouseId());
            object.put("productPreSumNumber", s.getProductPreSumNumber());
            object.put("productNumber", s.getProductNumber());
            object.put("oneInPrice", s.getOneInPrice());
            object.put("oneLowestPrice", s.getOneLowestPrice());
            object.put("sumLowestPrice", s.getSumLowestPrice());
            object.put("taskStartTime", s.getTaskStartTime());
            object.put("latestFinishTime", s.getLatestFinishTime());
            object.put("finishState", s.getFinishState());
            array.add(object);
        }
        return array;
    }

    @Override
    public SaleTask getBySaleTaskId(String saleTaskId) {
        return saleTaskDao.selectByPrimaryKey(saleTaskId);
    }

    @Override
    public boolean updateProductNum(SaleTask saleTask) {
        return saleTaskDao.updateByPrimaryKeySelective(saleTask) > 0;
    }

    @Override
    public boolean deleteBySaleTaskId(String saleTaskId) {
        return saleTaskDao.deleteByPrimaryKey(saleTaskId)>0;
    }
}
