package com.xxx.servlet;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserAccountLoginServlet",urlPatterns = "/userAccountLogin")
public class UserAccountLoginServlet extends HttpServlet { // 登录

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 UserAccountLoginServlet============");

        //获取用户名和密码
        String username = request.getParameter("userPhone");
        String password = request.getParameter("userPassword");
        //测试输出
        System.out.println("输入账号为：" + username + ",密码为：" + password);

        UserAccountService userAccountService = new UserAccountServiceImpl();

        UserAccount userAccount = userAccountService.login(username, password);

        if (userAccount != null) {
            System.out.println("登录成功！");
            //把登录成功的员工的employee丢进session
            request.getSession().setAttribute("userAccount",userAccount);

            UserDataService userDataService = new UserDataServiceImpl();

            UserData userData = userDataService.selectByUserId(userAccount.getUserId());

            request.getSession().setAttribute("userData",userData);


            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();

        } else {
            System.out.println("登录失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();

        }

    }
}
