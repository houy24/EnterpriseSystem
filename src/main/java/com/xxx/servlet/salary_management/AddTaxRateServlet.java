package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Position;
import com.xxx.pojo.TaxRate;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.TaxRate.TaxRateService;
import com.xxx.service.TaxRate.TaxRateServiceImpl;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addTaxRate")
public class AddTaxRateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 AddTaxRateServlet============");

        // taxRateId 为随机生成的UUID，后台服务中生成
        String moneyStr = request.getParameter("money");
        String rateStr = request.getParameter("rate");


        Double money = Double.parseDouble(moneyStr);
        Double rate = Double.parseDouble(rateStr);

        String taxRateId = UUIDUtils.getUUIDArg("taxRate-r");

        TaxRate taxRate = new TaxRate(taxRateId,money,rate,0);

        System.out.println(taxRateId + "," + money + "," + rate + "," + 0);

        TaxRateService taxRateService = new TaxRateServiceImpl();

        // 无主建插入，业务层自动生成 UUID
        boolean flag = taxRateService.insertTaxRateNoPrimaryKey(taxRate);

        if (flag == true) {
            System.out.println("添加成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("添加失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
