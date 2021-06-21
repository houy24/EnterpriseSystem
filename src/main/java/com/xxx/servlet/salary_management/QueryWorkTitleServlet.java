package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserData;
import com.xxx.pojo.WorkTitle;
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

@WebServlet("/queryWorkTitle")
public class QueryWorkTitleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 QueryWorkTitleServlet============");
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

        WorkTitleService workTitleService = new WorkTitleServiceImpl();

        WorkTitle workTitle = workTitleService.getWorkTitleById(userData.getWorkTitleId());

        if (null == workTitle) {
            System.out.println("查询失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
            return;
        }

        System.out.println("查询成功！");
        // 返回true
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        jsonObject.put("nowWorkTitle",workTitle); // 职称
        jsonObject.put("nowUserData",userData); // 用户信息
        out.print(jsonObject);
        out.close();

    }
}
