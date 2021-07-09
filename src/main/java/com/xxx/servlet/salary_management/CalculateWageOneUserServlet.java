package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Position;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.Wage;
import com.xxx.pojo.WageContent;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
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

@WebServlet("/calculateWageOneUser")
public class CalculateWageOneUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 CalculateWageOneUserServlet============");

        // 接收参数
        String timeMonthSearch2 = request.getParameter("timeMonthSearch2");
        String userPhone = request.getParameter("userPhone");

        // 时间校验
        String nowTime = MyDateTimeUtils.getNowTime().substring(0,7);
        if (timeMonthSearch2.compareTo(nowTime) > 0) {
            System.out.println("不是结算本月和之前的工资，日期不正确！");
            // 返回 userNotExists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","errorMonthSearch");
            out.print(jsonObject);
            out.close();
            return;
        }

        System.out.println(timeMonthSearch2 + "," + userPhone);

        UserAccountService userAccountService = new UserAccountServiceImpl();

        UserAccount userAccount = userAccountService.getUserAccountByUserPhone(userPhone);

        if (null == userAccount) {
            // 员工不存在
            System.out.println("员工不存在！");
            // 返回 userNotExists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","userNotExists");
            out.print(jsonObject);
            out.close();
            return;
        }

        // 员工存在
        WageService wageService = new WageServiceImpl();

        Wage wageByUserIdAndMonth = wageService.getWageByUserIdAndMonth(userAccount.getUserId(), timeMonthSearch2);

        if (null != wageByUserIdAndMonth) {
            // 该月工资已经结算了，返回是否需要重新结算，该月工资
            System.out.println("工资已经结算，是否重新结算工资。。。");
            // 返回 userNotExists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","exists");
            out.print(jsonObject);
            out.close();
            return;
        }

        // 该月工资还未结算，需要结算该月工资
        System.out.println("工资结算中。。。");

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
