package com.xxx.servlet;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleRecord;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;
import com.xxx.utils.ServletGetJSONUtils;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "data",urlPatterns = "/AddOneSaleRecord")
public class AddOneSaleRecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 AddOneSaleRecordServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        JSONObject json = ServletGetJSONUtils.getJSONObject(request,response);

        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setSaleRecordId(UUIDUtils.getUUIDArg_("saleRecord_r"));
        saleRecord.setSaleRecordState("可见");
        saleRecord.setUserId(json.getString("userId"));
        saleRecord.setSaleTaskId(json.getString("salesTaskId"));
        saleRecord.setProductId(json.getString("productId"));
        saleRecord.setSaleNumber(Integer.parseInt(json.getString("saleNumber")));
        saleRecord.setSaleOneMoney(Double.parseDouble(json.getString("saleOneMoney")));
        saleRecord.setSaleSumMoney(Double.parseDouble(json.getString("saleSumMoney")));
        Date time = new Date();
        saleRecord.setSaleFinishTime(time);

        SalesRecordService service = new SalesRecordServiceImpl();
        int i = service.insertOneSaleRecord(saleRecord);

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        if(i == 0)
            jsonObject.put("flag","false");
        else
            jsonObject.put("flag","true");
        out.print(jsonObject);
        out.close();
    }
}
