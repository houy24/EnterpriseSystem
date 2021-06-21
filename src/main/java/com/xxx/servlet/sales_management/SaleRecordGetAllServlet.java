package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.SaleRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/SaleRecordGetAll")
public class SaleRecordGetAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        System.out.println("============进入 SaleRecordGetAllServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");

        SalesRecordService service = new SalesRecordServiceImpl();
        List<SaleRecord> saleRecords = service.getPersonalSalesRecord(userAccount.getUserId());

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        int count = saleRecords.size();
        if (page == null || limit == null) {
        } else {

            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);

            saleRecords = MyPageHelperUtils.getListByPagesLimit(saleRecords,page1,limit1);

        }
        JSONObject sales = new JSONObject();

        JSONArray jsonArray = service.toJSONArray(saleRecords);

        sales.put("code",0);
        sales.put("msg","123");
        sales.put("count",count);
        sales.put("data",jsonArray);
        System.out.println(sales);
        PrintWriter out = response.getWriter();
        out.print(sales);
        out.close();

    }
}
