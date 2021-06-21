package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.ProductWarehouse;
import com.xxx.pojo.SaleTask;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
import com.xxx.service.SaleTaskService.SaleTaskService;
import com.xxx.service.SaleTaskService.SaleTaskServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetRemnantInventory")
public class GetRemnantInventoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 GetRemnantInventoryServlet============");

        ProductWarehouseService productWarehouseService = new ProductWarehouseServiceImpl();

        List<ProductWarehouse> productWarehouseList = productWarehouseService.getAllProductWarehouse();

        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        int count = productWarehouseList.size();
        if(page ==null || limit == null) {
        }else {
            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);
            productWarehouseList = MyPageHelperUtils.getListByPagesLimit(productWarehouseList,page1,limit1);
        }

        SaleTaskService saleTaskService = new SaleTaskServiceImpl();
        List<SaleTask> saleTasks = saleTaskService.getAllSaleTasks();
        for (int j = 0; j < productWarehouseList.size(); j++) {
            for (int i = 0; i < saleTasks.size(); i++) {
                System.out.println(saleTasks.get(i).getProductWarehouseId());
                System.out.println(productWarehouseList.get(j).getProductWarehouseId());
                if (saleTasks.get(i).getProductWarehouseId().equals(productWarehouseList.get(j).getProductWarehouseId())){
                    productWarehouseList.get(j).setProductNumber(productWarehouseList.get(j).getProductNumber()-saleTasks.get(i).getProductNumber());
                }
            }
        }
        JSONArray json = productWarehouseService.toJSONArray(productWarehouseList);

        JSONObject object = new JSONObject();
        object.put("code",0);
        object.put("msg","productWarehouse");
        object.put("count",count);
        object.put("data",json);
        PrintWriter out = response.getWriter();
        out.print(object);
        out.close();
    }
}
