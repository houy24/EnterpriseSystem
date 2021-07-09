package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.PersonalResume;
import com.xxx.pojo.SignInRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.PersonalResume.PersonalResumeService;
import com.xxx.service.PersonalResume.PersonalResumeServiceImpl;
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

@WebServlet("/PersonalSign_in")
public class PersonalSign_inServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalSign_inServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        SignInRecordService signInRecordService = new SignInRecordServiceImpl();
        int flag = signInRecordService.selectDayByUserId(userAccount.getUserId());

        if (flag == 1){
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }else {

            SignInRecord signInRecord = new SignInRecord();
            String signInId = UUIDUtils.getUUIDArg("signIn-");
            UserDataService userDataService = new UserDataServiceImpl();
            UserData userData = userDataService.selectByUserId(userAccount.getUserId());

            signInRecord.setSignInId(signInId);
            signInRecord.setUserId(userAccount.getUserId());
            signInRecord.setUserName(userData.getUserName());
            signInRecord.setSignInTime(new Date());

            signInRecordService.insertSelective(signInRecord);

            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        }

    }
}
