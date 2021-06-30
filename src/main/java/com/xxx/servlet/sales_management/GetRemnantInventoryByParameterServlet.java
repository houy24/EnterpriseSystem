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

@WebServlet(name = "GetRemnantInventoryByParameterServlet",urlPatterns = "/getRemnantInventoryByParameter")
public class GetRemnantInventoryByParameterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetRemnantInventoryByParameterServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        String productType = request.getParameter("productType");
        String productName = request.getParameter("productName");
        System.out.println(productType+","+productName);
        System.out.println(productType.equals(""));
        System.out.println(productName.equals(""));
        ProductWarehouseService service = new ProductWarehouseServiceImpl();
        List<ProductWarehouse> productWarehouses;
        if (!productType.equals("") && productName.equals("")){
            productWarehouses = service.getByProductType(productType);
        } else if (productType.equals("") && productName.equals("")){
            productWarehouses = service.getAllProductWarehouse();
        } else {
            productWarehouses = service.getByProductName(productName);
        }
        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        int count = productWarehouses.size();
        if(page ==null || limit == null) {
        }else {
            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);
            productWarehouses = MyPageHelperUtils.getListByPagesLimit(productWarehouses,page1,limit1);
        }

        SaleTaskService saleTaskService = new SaleTaskServiceImpl();
        List<SaleTask> saleTasks = saleTaskService.getAllSaleTasks();
        for (int j = 0; j < productWarehouses.size(); j++) {
            for (int i = 0; i < saleTasks.size(); i++) {
                if (saleTasks.get(i).getProductWarehouseId().equals(productWarehouses.get(j).getProductWarehouseId())){
                    productWarehouses.get(j).setProductNumber(productWarehouses.get(j).getProductNumber()-saleTasks.get(i).getProductNumber());
                }
            }
        }
        JSONArray jsonArray = service.toJSONArray(productWarehouses);
        JSONObject sales = new JSONObject();
        sales.put("code",0);
        sales.put("msg","productWarehouse");
        sales.put("count",count);
        sales.put("data",jsonArray);
        PrintWriter out = response.getWriter();
        out.print(sales);
        out.close();

    }
}
