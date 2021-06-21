package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleRecord;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;
import com.xxx.utils.ServletGetJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateOneSaleRecord")
public class UpdateOneSaleRecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 UpdateOneSaleRecordServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        JSONObject json = ServletGetJSONUtils.getJSONObject(request, response);
        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setSaleRecordId(json.getString("saleRecordId"));
        saleRecord.setUserId(json.getString("userId"));
        saleRecord.setSaleTaskId(json.getString("saleTaskId"));
        saleRecord.setProductId(json.getString("productId"));
        saleRecord.setSaleNumber(Integer.parseInt(json.getString("saleNumber")));
        saleRecord.setSaleOneMoney(Double.parseDouble(json.getString("saleOneMoney")));
        saleRecord.setSaleSumMoney(Double.parseDouble(json.getString("saleSumMoney")));
        saleRecord.setSaleRecordState(json.getString("saleRecordState"));

        SalesRecordService service = new SalesRecordServiceImpl();
        boolean flag = service.updateOneSaleRecord(saleRecord);
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);
        out.print(jsonObject);
        out.close();
    }

}
