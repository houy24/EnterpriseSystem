package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PersonalPassword")
public class PersonalPasswordServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalPasswordServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        //获取用旧密码和新密码
        String oldPassword = request.getParameter("old");
        String newPassword1 = request.getParameter("new1");
        String newPassword2 = request.getParameter("new2");
        //测试输出
        System.out.println("旧密码位：" + oldPassword + ",新密码位：" + newPassword1 + ",新密码位：" + newPassword2);

        if (userAccount.getUserPassword().equals(oldPassword)) {
            System.out.println("原密码正确！");
            if (newPassword1.equals(newPassword2)){
                System.out.println("两次新密码相同！");

                UserAccountService userAccountService = new UserAccountServiceImpl();
                userAccountService.updateUserPasswordById(userAccount.getUserId(),newPassword1);
                // 返回true
                PrintWriter out = response.getWriter();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("check","true");
                jsonObject.put("checks","true");
                out.print(jsonObject);
                out.close();
            }else {
                System.out.println("修改密码失败！");
                // 返回false
                PrintWriter out = response.getWriter();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("checks","false");
                out.print(jsonObject);
                out.close();
            }
        } else {
            System.out.println("修改密码失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();

        }

    }
}
