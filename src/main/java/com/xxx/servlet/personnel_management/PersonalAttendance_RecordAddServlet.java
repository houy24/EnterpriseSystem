package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Department;
import com.xxx.pojo.SignInRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalDepartment.PersonalDepartmentService;
import com.xxx.service.PersonalDepartment.PersonalDepartmentServiceImpl;
import com.xxx.service.PersonalSignInRecord.SignInRecordService;
import com.xxx.service.PersonalSignInRecord.SignInRecordServiceImpl;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/PersonalAttendance_RecordAdd")
public class PersonalAttendance_RecordAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalAttendance_RecordAddServlet============");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String signInId  = UUIDUtils.getUUIDArg("signIn-");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String signIn = request.getParameter("signInTime");
        String signOut = request.getParameter("signOutTime");

        SignInRecord signInRecord =new SignInRecord();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date signInTime = ft.parse(signIn);
            Date signOutTime = ft.parse(signOut);

            signInRecord.setSignInId(signInId);
            signInRecord.setUserId(userId);
            signInRecord.setUserName(userName);
            signInRecord.setSignInTime(signInTime);
            signInRecord.setSignOutTime(signOutTime);

            System.out.println(signInRecord.toString());

            SignInRecordService signInRecordService = new SignInRecordServiceImpl();
            signInRecordService.insert(signInRecord);
        }catch (Exception e){
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        out.print(jsonObject);
        out.close();

    }
}
