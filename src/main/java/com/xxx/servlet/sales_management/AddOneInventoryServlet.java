package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;
import com.xxx.pojo.ProductWarehouse;
import com.xxx.service.ProductService.ProductService;
import com.xxx.service.ProductService.ProductServiceImpl;
import com.xxx.service.ProductWarehouseService.ProductWarehouseService;
import com.xxx.service.ProductWarehouseService.ProductWarehouseServiceImpl;
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

@WebServlet(name = "AddOneInventoryServlet",urlPatterns = "/addOneInventory")
public class AddOneInventoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 AddOneInventoryServlet============");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        JSONObject json = ServletGetJSONUtils.getJSONObject(request,response);
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.selectPyProductName(json.getString("productName"));
        boolean flag = false;
        if (product==null){
            ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
            ProductType productType = productTypeDao.selectByProductTypeName(json.getString("productTypeName"));
            Product product1 = new Product();
            product1.setProductId(UUIDUtils.getUUIDArg_("product-p"));
            product1.setProductName(json.getString("productName"));
            product1.setProductSalePrice(Double.parseDouble(json.getString("oneInPrice"))*1.2);
            product1.setProductDescription("");
            product1.setProductTypeId(productType.getProductTypeId());
            ProductService service1 = new ProductServiceImpl();
            flag = service1.addOneProduct(product1);
            product = product1;
        }
        if (flag) {
            ProductWarehouse productWarehouse = new ProductWarehouse();
            productWarehouse.setProductWarehouseId(UUIDUtils.getUUIDArg_("productWarehouse-w"));
            productWarehouse.setProductId(product.getProductId());
            productWarehouse.setProductName(product.getProductName());
            productWarehouse.setProductNumber(Integer.parseInt(json.getString("productNumber")));
            Date time = new Date();
            productWarehouse.setInWareHouseTime(time);
            productWarehouse.setProductionAddress(json.getString("productionAddress"));
            productWarehouse.setOneInPrice(Double.parseDouble(json.getString("oneInPrice")));

            double totalPrice = productWarehouse.getOneInPrice() * productWarehouse.getProductNumber();
            totalPrice = (double) Math.round(totalPrice * 100) / 100;
            productWarehouse.setTotalInPrice(totalPrice);

            System.out.println(productWarehouse);
            ProductWarehouseService service = new ProductWarehouseServiceImpl();
            flag = service.addOneInventory(productWarehouse);
        }
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);
        out.print(jsonObject);
        out.close();
    }
}