package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteOneSaleRecord")
public class DeleteOneSaleRecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 DeleteOneSaleRecordServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        String saleRecordId = request.getParameter("saleRecordId");
        SalesRecordService service = new SalesRecordServiceImpl();
        boolean flag = service.deleteOneSaleRecord(saleRecordId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);

        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }
}
