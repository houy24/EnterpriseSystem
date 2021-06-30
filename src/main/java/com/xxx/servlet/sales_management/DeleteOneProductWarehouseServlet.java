package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteOneProductWarehouseServlet",urlPatterns = "/deleteOneProductWarehouse")
public class DeleteOneProductWarehouseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 DeleteOneProductWarehouseServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        String productWarehouseId = request.getParameter("productWarehouseId");
        ProductWarehouseService service = new ProductWarehouseServiceImpl();
        boolean flag = service.deleteByProductWarehouseId(productWarehouseId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);

        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }
}
