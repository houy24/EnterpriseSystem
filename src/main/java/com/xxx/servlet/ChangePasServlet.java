package com.xxx.servlet;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ChangePas")
public class ChangePasServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 ChangePasServlet============");

        PrintWriter out = response.getWriter();

        // 忘记密码
        /* window.parent.location 能跳出 iframe 层  */
        out.write("<script type=\"text/javascript\" charset=\"utf-8\" >alert('请联系管理员 1508564817@qq.com 找回密码 ！');" +
                "window.parent.location.href=\"/\";</script>");
    }
}
