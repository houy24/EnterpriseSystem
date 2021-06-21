package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleTask;
import com.xxx.service.SaleTaskService.SaleTaskService;
import com.xxx.service.SaleTaskService.SaleTaskServiceImpl;
import com.xxx.utils.ServletGetJSONUtils;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addOneSaleTask")
public class AddOneSaleTaskServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 AddOneSaleTaskServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        JSONObject json = ServletGetJSONUtils.getJSONObject(request, response);

        SaleTaskService service = new SaleTaskServiceImpl();
        SaleTask saleTask = new SaleTask();
        saleTask.setSaleTaskId(UUIDUtils.getUUIDArg_("saleTask-t"));
        saleTask.setUserId(json.getString("userId"));
        saleTask.setProductId(json.getString("productId"));
        saleTask.setProductWarehouseId(json.getString("productWarehouseId"));
        saleTask.setProductPreSumNumber(Integer.parseInt(json.getString("productNumber")));
        saleTask.setProductNumber(Integer.parseInt(json.getString("productNumber")));
        saleTask.setOneInPrice(Double.parseDouble(json.getString("oneInPrice")));
        saleTask.setOneLowestPrice(Double.parseDouble(json.getString("oneLowestPrice")));
        saleTask.setSumLowestPrice(Double.parseDouble(json.getString("sumLowestPrice")));

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        // String转Date
        String startTime = json.getString("startTime")+" 08:00:00";
        String endTime = json.getString("endTime")+" 08:00:00";
        try {
            date1 = format1.parse(startTime);
            date2 = format1.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        saleTask.setTaskStartTime(date1);
        saleTask.setLatestFinishTime(date2);

        saleTask.setFinishState("等待完成");

        boolean flag = service.insertOneSaleTask(saleTask);
        JSONObject object = new JSONObject();
        object.put("flag",flag);
        PrintWriter out = response.getWriter();
        out.print(object);
        out.close();
    }

}
