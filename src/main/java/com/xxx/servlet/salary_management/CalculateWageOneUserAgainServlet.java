package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.Wage;
import com.xxx.pojo.WageContent;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.Wage.WageService;
import com.xxx.service.Wage.WageServiceImpl;
import com.xxx.service.WageContent.WageContentService;
import com.xxx.service.WageContent.WageContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

@WebServlet("/calculateWageOneUserAgain")
public class CalculateWageOneUserAgainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 CalculateWageOneUserAgainServlet============");

        // 接收参数
        String timeMonthSearch2 = request.getParameter("timeMonthSearch2");
        String userPhone = request.getParameter("userPhone");

        System.out.println(timeMonthSearch2 + "," + userPhone);

        UserAccountService userAccountService = new UserAccountServiceImpl();

        UserAccount userAccount = userAccountService.getUserAccountByUserPhone(userPhone);

        // 员工存在
        WageService wageService = new WageServiceImpl();

        // 之前的记录
        Wage wageByUserIdAndMonth = wageService.getWageByUserIdAndMonth(userAccount.getUserId(), timeMonthSearch2);

        // 该月工资已经结算了，重新结算，该月工资
        System.out.println("该月工资已经结算了，重新结算，该月工资。。。");

        WageContentService wageContentService = new WageContentServiceImpl();

        WageContent wageContent = null;
        try {
            wageContent = wageContentService.calculateWageContentByUserIdAndMonth(userAccount.getUserId(), timeMonthSearch2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null == wageContent) {
            System.out.println("工资结算失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        // 结算完成
        boolean flag = wageContentService.insertWageAndWageContent(wageContent); // 添加两个表的记录

        if (flag == true) {
            System.out.println("工资结算成功！");
            // 删除之前的结算记录
            wageContentService.deleteWageAndWageContentByWageId(wageByUserIdAndMonth.getWageId());
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("工资结算失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
