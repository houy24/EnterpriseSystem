package com.xxx.service.SaleTaskService;

import com.alibaba.fastjson.JSONArray;
import com.xxx.pojo.SaleTask;

import java.util.List;

public interface SaleTaskService {
    List<SaleTask> getAllSaleTasks();
    boolean insertOneSaleTask(SaleTask saleTask);
    List<SaleTask> getSaleTaskByUserId(String userId);
    JSONArray toJSONArray(List<SaleTask> saleTaskList);
    SaleTask getBySaleTaskId(String saleTaskId);
    boolean updateProductNum(SaleTask saleTask);
    boolean deleteBySaleTaskId(String saleTaskId);
}
