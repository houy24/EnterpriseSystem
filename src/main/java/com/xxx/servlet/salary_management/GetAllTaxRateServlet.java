package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.TaxRate;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.TaxRate.TaxRateService;
import com.xxx.service.TaxRate.TaxRateServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllTaxRate")
public class GetAllTaxRateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllTaxRateServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        // 接收参数
        String searchMoneyStr = request.getParameter("searchMoney");
        System.out.println("searchMoneyStr => " + searchMoneyStr);

        if (null == searchMoneyStr || searchMoneyStr == "") {
            searchMoneyStr = "99999999999999";
        }

        Double searchMoney = Double.parseDouble(searchMoneyStr);

        TaxRateService taxRateService = new TaxRateServiceImpl();
        List<TaxRate> allTaxRate = taxRateService.selectAll();

        System.out.println(allTaxRate);

        boolean f = false;

        // 搜索过滤。。。
        for (int i = 0; i < allTaxRate.size(); i++) {
            if (f == true) {
                allTaxRate.remove(i);
                i--;
            } else if(f == false) {
                if (allTaxRate.get(i).getMoney() >= searchMoney) {
                    f = true;
                }
            }
        }

        System.out.println(allTaxRate);

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");

        if (pageStr == null || limitStr == null) {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",allTaxRate.size());
            jsonObject.put("data",allTaxRate);
            out.print(jsonObject);
            out.close();
            return;
        }

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        System.out.println(page + "," + limit);

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allTaxRate.size());
        allTaxRate = MyPageHelperUtils.getListByPagesLimit(allTaxRate,page,limit);
        jsonObject.put("data",allTaxRate);
        out.print(jsonObject);
        out.close();
    }
}
