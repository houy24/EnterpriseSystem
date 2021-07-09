package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.WageContent;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.WageContent.WageContentService;
import com.xxx.service.WageContent.WageContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calculateWageAllUserAgain")
public class CalculateWageAllUserAgainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 CalculateWageAllUserServlet============");

        // 接收参数
        String timeMonthSearch1 = request.getParameter("timeMonthSearch1");

        System.out.println(timeMonthSearch1);

        UserAccountService userAccountService = new UserAccountServiceImpl();

        List<UserAccount> userAccountList = userAccountService.getAllUserAccount();

        WageContentService wageContentService = new WageContentServiceImpl();

        // 该月份的所有工资
        List<WageContent> wageContentByMonth = wageContentService.getWageContentByMonth(timeMonthSearch1);

        // 该月工资已经接上结算，需要重新结算该月工资，对所有员工
        System.out.println("工资结算中。。。");

        List<WageContent> wageContentListNow = new ArrayList<WageContent>();

        boolean flag = true;

        try {
            for (UserAccount userAccount : userAccountList) { // 一个一个员工结算
                WageContent wageContent = wageContentService.calculateWageContentByUserIdAndMonth(userAccount.getUserId(), timeMonthSearch1);

                if (null == wageContent) {
                    flag = false; // 结算失败
                    break;
                } else {
                    wageContentListNow.add(wageContent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag == false) {
            System.out.println("工资结算失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        // 结算成功

        // 添加
        for (WageContent wageContent : wageContentListNow) {
            wageContentService.insertWageAndWageContent(wageContent);
        }

        // 删除
        for (WageContent wageContent : wageContentByMonth) {
            wageContentService.deleteWageAndWageContentByWageId(wageContent.getWageId());
        }

        System.out.println("工资结算成功！");
        // 返回true
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        out.print(jsonObject);
        out.close();

    }
}
