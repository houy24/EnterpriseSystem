package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SignInRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.PersonalSignInRecord.SignInRecordService;
import com.xxx.service.PersonalSignInRecord.SignInRecordServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/PersonalSign_out")
public class PersonalSign_outServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalSign_outServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        SignInRecordService signInRecordService = new SignInRecordServiceImpl();
        int flag = signInRecordService.selectDayByUserId(userAccount.getUserId());

        if (flag == 1){
            SignInRecord signInRecord = new SignInRecord();
            Date date = new Date();
            SignInRecord signInRecords = signInRecordService.selectTodayByUserId(userAccount.getUserId());

            signInRecord.setSignInId(signInRecords.getSignInId());
            signInRecord.setSignOutTime(new Date());

            if (signInRecords.getSignOutTime() != null){
                PrintWriter out = response.getWriter();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("check","true");
                jsonObject.put("upd","true");
                out.print(jsonObject);
                out.close();
            }else {
                int flags = signInRecordService.updateByPrimaryKeySelective(signInRecord);

                PrintWriter out = response.getWriter();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("check","true");
                jsonObject.put("update","false");
                out.print(jsonObject);
                out.close();
            }
        }else {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
