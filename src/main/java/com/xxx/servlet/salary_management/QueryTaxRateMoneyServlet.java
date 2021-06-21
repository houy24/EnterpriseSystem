package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.TaxRate;
import com.xxx.pojo.UserData;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.TaxRate.TaxRateService;
import com.xxx.service.TaxRate.TaxRateServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/queryTaxRateMoney")
public class QueryTaxRateMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 QueryTaxRateMoneyServlet============");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        // 接收参数
        String taxRateType = request.getParameter("taxRateType");
        String preMoneyStr = request.getParameter("preMoney");
        String baseMoneyStr = request.getParameter("baseMoney");

        System.out.println(taxRateType + "," + preMoneyStr + "," + baseMoneyStr);

        // 税前总工资
        double preMoney = Double.parseDouble(preMoneyStr);
        // 起征点
        double baseMoney = Double.parseDouble(baseMoneyStr);

        // 五险一金
        double fiveOneMoney = 0;
        // 养老保险，医疗保险，失业保险，工伤保险，生育保险，住房公积金
        double oldEnsure = 0 , medicalEnsure = 0 , lostJobEnsure = 0
                , workHurtEnsure = 0 , birthEnsure = 0 , houseFundEnsure = 0 ;

        if (taxRateType.equals("2")) {
            // 计算五险一金
            oldEnsure = 0.008 * preMoney;
            medicalEnsure = 0.002 * preMoney + 3.0;
            lostJobEnsure = 0.002 * preMoney;
            workHurtEnsure = 0 * preMoney;
            birthEnsure = 0 * preMoney;
            houseFundEnsure = 0.12 * preMoney;

            fiveOneMoney = oldEnsure + medicalEnsure + lostJobEnsure
                    + workHurtEnsure + birthEnsure + houseFundEnsure;
        }

        // 先减掉 五险一金
        preMoney -= fiveOneMoney;

        // 可能起征点低于总工资，改变起征点
        baseMoney = Math.min(baseMoney,preMoney);

        // 减去起征点，不能减到0，最小0元
        preMoney = Math.max(0,preMoney - baseMoney);

        // 计算税率

        // 应交税款，税后工资
        double shouldTaxRateMoney = -1 , realyMoney = 0;

        TaxRateService taxRateService = new TaxRateServiceImpl();

        List<TaxRate> taxRateList = taxRateService.selectAll(); // 所有税率

        for(TaxRate taxRate : taxRateList) {
            if (preMoney <= taxRate.getMoney()) {
                shouldTaxRateMoney = preMoney * taxRate.getRate() - taxRate.getQuicklyReduce();
                break;
            }
        }

        if (shouldTaxRateMoney == -1) {
            System.out.println("查询失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        preMoney += baseMoney; // 加回起征点工资

        // 税后工资
        realyMoney = preMoney - shouldTaxRateMoney;

        System.out.println("查询成功！");
        // 返回true
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        jsonObject.put("fiveOneMoney",fiveOneMoney); // 五险一金
        jsonObject.put("shouldTaxRateMoney",shouldTaxRateMoney); // 应交税款
        jsonObject.put("realyMoney",realyMoney); // 税后工资
        out.print(jsonObject);
        out.close();

    }
}
