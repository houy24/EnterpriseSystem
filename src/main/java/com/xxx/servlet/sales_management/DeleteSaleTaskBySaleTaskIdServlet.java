package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleTask;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.service.SaleTaskService.SaleTaskService;
import com.xxx.service.SaleTaskService.SaleTaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteSaleTaskBySaleTaskIdServlet",urlPatterns = "/deleteBySaleTaskId")
public class DeleteSaleTaskBySaleTaskIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 DeleteSaleTaskBySaleTaskIdServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        String saleTaskId = request.getParameter("saleTaskId");
        SaleTaskService service = new SaleTaskServiceImpl();
        boolean flag = false;
        SaleTask saleTask = service.getBySaleTaskId(saleTaskId);
        if (saleTask.getProductNumber() == saleTask.getProductPreSumNumber()) {
            flag = service.deleteBySaleTaskId(saleTaskId);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);

        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }
}
