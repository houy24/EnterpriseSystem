package com.xxx.servlet.personnel_management;

import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalDepartment.PersonalDepartmentService;
import com.xxx.service.PersonalDepartment.PersonalDepartmentServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PersonalDepartment_informationDel")
public class PersonalDepartment_informationDelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalDepartment_informationDelServlet============");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String departmentId = request.getParameter("userId");
        System.out.println("部门编号："+departmentId);

        PersonalDepartmentService personalDepartmentService = new PersonalDepartmentServiceImpl();
        personalDepartmentService.deleteByPrimaryKey(departmentId);

        request.getRequestDispatcher("/pages/personnel_management/BackPersonalDepartment_information.jsp").forward(request,response);

    }
}
