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
import com.xxx.utils.MyDateTimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calculateWageAllUser")
public class CalculateWageAllUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 CalculateWageAllUserServlet============");

        // 接收参数
        String timeMonthSearch1 = request.getParameter("timeMonthSearch1");

        // 时间校验
        String nowTime = MyDateTimeUtils.getNowTime().substring(0,7);
        if (timeMonthSearch1.compareTo(nowTime) > 0) {
            System.out.println("不是结算本月和之前的工资，日期不正确！");
            // 返回 userNotExists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","errorMonthSearch");
            out.print(jsonObject);
            out.close();
            return;
        }


        System.out.println(timeMonthSearch1);

        UserAccountService userAccountService = new UserAccountServiceImpl();

        List<UserAccount> userAccountList = userAccountService.getAllUserAccount();

        WageContentService wageContentService = new WageContentServiceImpl();

        // 该月份的所有工资
        List<WageContent> wageContentByMonth = wageContentService.getWageContentByMonth(timeMonthSearch1);

        if (null != wageContentByMonth && wageContentByMonth.size() != 0) {
            // 该月工资已经结算了，返回是否需要重新结算，该月工资
            System.out.println("工资已经结算，是否重新结算工资。。。");
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","exists");
            out.print(jsonObject);
            out.close();
            return;
        }


        // 该月工资还未结算，需要结算该月工资，对所有员工
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
