package com.xxx.servlet.personnel_management;

import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalSignInRecord.SignInRecordService;
import com.xxx.service.PersonalSignInRecord.SignInRecordServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PersonalAttendance_RecordDel")
public class PersonalAttendance_RecordDelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalAttendance_RecordDelServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String signInId = request.getParameter("userId");
        System.out.println("考勤记录编号："+signInId);

        SignInRecordService signInRecordService = new SignInRecordServiceImpl();
        signInRecordService.deleteByPrimaryKey(signInId);

        request.getRequestDispatcher("/pages/personnel_management/BackPersonalAttendance_Record.jsp").forward(request,response);

    }
}
