package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.PersonalResume;
import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalResume.PersonalResumeService;
import com.xxx.service.PersonalResume.PersonalResumeServiceImpl;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PersonalResumeS")
public class PersonalResumeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalResumeServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        PersonalResumeService personalResumeService = new PersonalResumeServiceImpl();
        PersonalResume personalResume = personalResumeService.selectAllByUserId(userAccount.getUserId());
        System.out.println("个人简历："+personalResume);

        request.setAttribute("personalResume",personalResume);
        request.getRequestDispatcher("/pages/personnel_management/PersonalResume.jsp").forward(request,response);

    }
}
