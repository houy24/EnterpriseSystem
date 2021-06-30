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

@WebServlet(name = "UserAccountLogoutServlet",urlPatterns = "/userAccountLogout")
public class UserAccountLogoutServlet extends HttpServlet { // 登录

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 UserAccountLogoutServlet============");

        request.getSession().removeAttribute("userAccount");
        request.getSession().removeAttribute("userData");

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }
}
