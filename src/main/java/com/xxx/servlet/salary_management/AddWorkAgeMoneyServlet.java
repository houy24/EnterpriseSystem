package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.pojo.WorkTitle;
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

@WebServlet("/addWorkAgeMoney")
public class AddWorkAgeMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 AddWorkAgeMoneyServlet============");

        String workAgeStr = request.getParameter("workAge");
        String baseAgeMoneyStr = request.getParameter("baseAgeMoney");

        Integer workAge = Integer.parseInt(workAgeStr);
        Double baseAgeMoney = Double.parseDouble(baseAgeMoneyStr);

        System.out.println(workAgeStr + "," + baseAgeMoneyStr);

        WorkAgeMoney workAgeMoney = new WorkAgeMoney(workAge,baseAgeMoney);

        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl();

        boolean flag = workAgeMoneyService.insertWorkAgeMoney(workAgeMoney);

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
