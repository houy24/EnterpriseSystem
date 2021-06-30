package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.ProductWarehouse;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetAllInventoryServlet",urlPatterns = "/getAllInventory")
public class GetAllInventoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        System.out.println("============进入 GetAllInventoryServlet============");
        ProductWarehouseService service = new ProductWarehouseServiceImpl();
        List<ProductWarehouse> productWarehouses = service.getAllProductWarehouse();

        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        int count = productWarehouses.size();
        if(page ==null || limit == null) {
        }else {
            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);
            productWarehouses = MyPageHelperUtils.getListByPagesLimit(productWarehouses,page1,limit1);
        }
        JSONArray jsonArray = service.toJSONArray(productWarehouses);
        JSONObject sales = new JSONObject();
        sales.put("code",0);
        sales.put("msg","productWarehouse");
        sales.put("count",count);
        sales.put("data",jsonArray);
        System.out.println(sales);
        PrintWriter out = response.getWriter();
        out.print(sales);
        out.close();
    }
}
