package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleRecord;
import com.xxx.pojo.UserAccount;
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

@WebServlet(name = "GetSaleRecordByTimeServlet",urlPatterns = "/getSaleRecordByTime")
public class GetSaleRecordByTimeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 GetSaleRecordByTimeServlet============");

        String startTime = request.getParameter("startTime") + " 00:00:00";
        String endTime = request.getParameter("endTime") + " 23:59:59";

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");

        SalesRecordService service = new SalesRecordServiceImpl();
        List<SaleRecord> saleRecords = service.getSaleRecordByUserAndTime(userAccount.getUserId(), startTime, endTime);

        String limit = request.getParameter("limit");
        String page = request.getParameter("page");

        int count = saleRecords.size();
        if(page!=null&&limit!=null){

            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);

            saleRecords = MyPageHelperUtils.getListByPagesLimit(saleRecords,page1,limit1);
        }
        JSONArray jsonArray = service.toJSONArray(saleRecords);
        JSONObject sales = new JSONObject();
        sales.put("code",0);
        sales.put("msg","salesRecord");
        sales.put("count",count);
        sales.put("data",jsonArray);
        System.out.println(sales);
        PrintWriter out = response.getWriter();
        out.print(sales);
        out.close();
    }
}
