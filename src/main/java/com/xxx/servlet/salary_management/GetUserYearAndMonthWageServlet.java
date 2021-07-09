package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.Wage.WageService;
import com.xxx.service.Wage.WageServiceImpl;
import com.xxx.utils.MyDateTimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getUserYearAndMonthWage")
public class GetUserYearAndMonthWageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetUserYearAndMonthWageServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        // 接收参数
        String userPhoneSearch = request.getParameter("userPhoneSearch");
        System.out.println("userPhoneSearch => " + userPhoneSearch);

        UserAccountService userAccountService = new UserAccountServiceImpl();
        UserAccount userAccount = null;

        if (null == userPhoneSearch || userPhoneSearch.equals("")) {   // 默认用户
            userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
            userPhoneSearch = userAccount.getUserPhone(); // 当前用户
        } else {
            userAccount = userAccountService.getUserAccountByUserPhone(userPhoneSearch);
        }

        if (userAccount == null) {
            System.out.println("查询失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        WageService wageService = new WageServiceImpl();
        UserDataService userDataService = new UserDataServiceImpl();

        // 查询当前用户 ，近三年， 年和月的工资情况
        int thisYear = Integer.parseInt(MyDateTimeUtils.getNowTime().substring(0,4));

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        String[] product = new String[5];   // 0-4
        product[0] = "product";

        String[][] monthList = new String[13][5]; // 0-11 0-4
        for (int i = 0; i <= 11; i++) {
            monthList[i][0] = (i+1) + "月"; // 1月 - 12月 标题
        }
        monthList[12][0] = "总收入"; // 总收入

        int head = 1;
        for (int year = thisYear - 2; year <= thisYear; year ++) {

            String nowYear = String.valueOf(year); // 当前年
            product[head] = nowYear;
            // 年收入
            double userYearWage = wageService.getUserYearWage(userAccount.getUserId(), nowYear);
            monthList[12][head] = String.valueOf(userYearWage);

            for (int i = 1; i <= 12; i++) {
                String nowYearMonth = null;
                if (i < 10) {
                    nowYearMonth = nowYear + "-0" + i;
                } else {
                    nowYearMonth = nowYear + "-" + i;
                }
                double userMonthWage = wageService.getUserMonthWage(userAccount.getUserId(), nowYearMonth);

                monthList[i-1][head] = String.valueOf(userMonthWage);
            }
            head ++;
        }

        jsonObject.put("check","true");
        UserData userData = userDataService.selectByUserId(userAccount.getUserId());
        jsonObject.put("userName",userData.getUserName());
        jsonObject.put("product",product);
        jsonObject.put("data1",monthList[0]);
        jsonObject.put("data2",monthList[1]);
        jsonObject.put("data3",monthList[2]);
        jsonObject.put("data4",monthList[3]);
        jsonObject.put("data5",monthList[4]);
        jsonObject.put("data6",monthList[5]);
        jsonObject.put("data7",monthList[6]);
        jsonObject.put("data8",monthList[7]);
        jsonObject.put("data9",monthList[8]);
        jsonObject.put("data10",monthList[9]);
        jsonObject.put("data11",monthList[10]);
        jsonObject.put("data12",monthList[11]);
        jsonObject.put("dataSum",monthList[12]);
        out.print(jsonObject);
        out.close();
        return;

    }
}
