package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserData;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyService;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyServiceImpl;
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

@WebServlet("/queryWorkAgeMoney")
public class QueryWorkAgeMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 QueryWorkAgeMoneyServlet============");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String userPhone = request.getParameter("userPhone");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");


        System.out.println(userPhone + "," + userName + "," + userEmail);

        UserDataService userDataService = new UserDataServiceImpl();

        UserData userData = userDataService.selectByUserPhone(userPhone);

        if (null == userData) {
            System.out.println("查询失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl();

        List<WorkAgeMoney> allWorkAgeMoney = workAgeMoneyService.getAllWorkAgeMoney();

        WorkAgeMoney workAgeMoney = new WorkAgeMoney(userData.getWorkAge(),0);
        double baseAgeMoney = -1;

        for (int i = 0; i < allWorkAgeMoney.size(); i++) {
            // 取小于等于当前工龄的最大基础工资
            if (allWorkAgeMoney.get(i).getWorkAge() <= workAgeMoney.getWorkAge()) {
                baseAgeMoney = Math.max(baseAgeMoney,allWorkAgeMoney.get(i).getBaseAgeMoney());
            } else {        // （可能有问题）
                break;
            }
        }

        if (baseAgeMoney == -1) {
            System.out.println("查询失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        workAgeMoney.setBaseAgeMoney(baseAgeMoney);
        System.out.println("查询成功！");
        // 返回true
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        jsonObject.put("nowWorkAgeMoney",workAgeMoney); // 工龄
        jsonObject.put("nowUserData",userData); // 用户信息
        out.print(jsonObject);
        out.close();

    }
}
