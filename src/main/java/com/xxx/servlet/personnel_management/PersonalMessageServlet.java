package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.PersonalResume;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.PersonalResume.PersonalResumeService;
import com.xxx.service.PersonalResume.PersonalResumeServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PersonalMessage")
public class PersonalMessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 PersonalMessageServlet============");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        UserDataService userDataService = new UserDataServiceImpl();
        UserData userData = userDataService.selectByUserId(userAccount.getUserId());
        System.out.println("个人信息："+userData);
        JSONObject json = (JSONObject) JSONObject.toJSON(userData);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.close();

    }
}
