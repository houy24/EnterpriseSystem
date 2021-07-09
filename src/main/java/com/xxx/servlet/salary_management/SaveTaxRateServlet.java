package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.TaxRate;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.TaxRate.TaxRateService;
import com.xxx.service.TaxRate.TaxRateServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveTaxRate")
public class SaveTaxRateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 SaveTaxRateServlet============");

        String taxRateId = request.getParameter("taxRateId");
        String moneyStr = request.getParameter("money");
        String rateStr = request.getParameter("rate");
        String quicklyReduceStr = request.getParameter("quicklyReduce");

        System.out.println(taxRateId + "," + moneyStr + "," + rateStr + "," + quicklyReduceStr);

        Double money = Double.parseDouble(moneyStr);
        Double rate = Double.parseDouble(rateStr);
        Double quicklyReduce = Double.parseDouble(quicklyReduceStr);

        TaxRate taxRate = new TaxRate(taxRateId,money,rate,quicklyReduce);

        TaxRateService taxRateService = new TaxRateServiceImpl();

        boolean flag = taxRateService.updateTaxRate(taxRate);

        if (flag == true) {
            System.out.println("保存成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("保存失败，税率信息不合法！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
