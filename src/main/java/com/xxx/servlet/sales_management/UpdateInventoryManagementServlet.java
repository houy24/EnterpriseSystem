package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.ProductWarehouse;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.utils.ServletGetJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateInventoryManagementServlet",urlPatterns = "/updateInventoryManagement")
public class UpdateInventoryManagementServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 UpdateInventoryManagementServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        JSONObject json = ServletGetJSONUtils.getJSONObject(request,response);
        System.out.println(json);
        ProductWarehouse productWarehouse = new ProductWarehouse();
        productWarehouse.setProductWarehouseId(json.getString("productWarehouseId"));
        productWarehouse.setProductId(json.getString("productId"));
        productWarehouse.setProductName(json.getString("productName"));
        productWarehouse.setProductNumber(Integer.parseInt(json.getString("productNumber")));
        productWarehouse.setProductionAddress(json.getString("productionAddress"));
        productWarehouse.setOneInPrice(Double.parseDouble(json.getString("oneInPrice")));
        productWarehouse.setTotalInPrice(Double.parseDouble(json.getString("totalInPrice")));

        System.out.println(productWarehouse.toString());

        ProductWarehouseService service = new ProductWarehouseServiceImpl();
        boolean flag = service.updateProductNumber(productWarehouse);
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);
        out.print(jsonObject);
        out.close();
    }

}
