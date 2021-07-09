package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyService;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteWorkAgeMoney")
public class DeleteWorkAgeMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 DeleteWorkAgeMoneyServlet============");

        String workAge = request.getParameter("workAge");

        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl();

        boolean flag = workAgeMoneyService.deleteWorkAgeMoney(Integer.parseInt(workAge));

        if (flag == true) {
            System.out.println("删除成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("删除失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
