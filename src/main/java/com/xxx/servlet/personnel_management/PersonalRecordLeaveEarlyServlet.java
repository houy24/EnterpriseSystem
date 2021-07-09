package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SignInRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalSignInRecord.SignInRecordService;
import com.xxx.service.PersonalSignInRecord.SignInRecordServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PersonalRecordLeaveEarly")
public class PersonalRecordLeaveEarlyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 PersonalRecordLeaveEarlyServlet============");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        System.out.println("limit:" +limit+" "+"page:"+page);
        SignInRecordService signInRecordService = new SignInRecordServiceImpl();
//        int count = saleRecordService.selectCountByUserId(userAccount.getUserId());
//        int last = count > 10*Integer.valueOf(limit) ? 10*Integer.valueOf(limit) : count;
        List<SignInRecord> signInRecords =signInRecordService.selectAllByUserIdLeaveEarly(userAccount.getUserId());
        System.out.println("早退记录："+signInRecords);
        signInRecords = MyPageHelperUtils.getListByPagesLimit(signInRecords,Integer.valueOf(page),Integer.valueOf(limit));
        System.out.println("分页后："+signInRecords);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",signInRecords.size());
        json.put("data",signInRecords);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.close();
//        request.setAttribute("json",json);
//        request.getRequestDispatcher("/pages/personnel_management/BackPersonalSalesRecord.jsp").forward(request,response);

    }
}
