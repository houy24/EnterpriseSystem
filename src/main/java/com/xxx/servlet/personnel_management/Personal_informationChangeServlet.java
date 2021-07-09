package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Personal_informationChange")
public class Personal_informationChangeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 Personal_informationChangeServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userSex = request.getParameter("userSex");
        String userEmail = request.getParameter("userEmail");
        String userAge = request.getParameter("userAge");
        String userType = request.getParameter("userType");
        String departmentId = request.getParameter("departmentId");
        String positionId = request.getParameter("positionId");
        String workTitleId = request.getParameter("workTitleId");
        String workAge = request.getParameter("workAge");
        String userPhoto = request.getParameter("userPhoto");

        UserData userData = new UserData();
        userData.setUserId(userId);
        userData.setUserName(userName);
        userData.setUserPhone(userPhone);
        userData.setUserSex(userSex);
        userData.setUserEmail(userEmail);
        userData.setUserAge(Integer.valueOf(userAge));
        userData.setUserType(userType);
        userData.setDepartmentId(departmentId);
        userData.setPositionId(positionId);
        userData.setWorkTitleId(workTitleId);
        userData.setWorkAge(Integer.valueOf(workAge));
        userData.setUserPhoto(userPhoto);

        System.out.println(userData.toString());

        UserDataService userDataService = new UserDataServiceImpl();
        userDataService.updateByPrimaryKey(userData);

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        out.print(jsonObject);
        out.close();

//        request.getRequestDispatcher("/pages/personnel_management/BackPersonnel_information.jsp").forward(request,response);

    }
}
