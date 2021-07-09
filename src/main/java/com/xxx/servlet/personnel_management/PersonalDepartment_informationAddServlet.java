package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Department;
import com.xxx.pojo.SignInRecord;
import com.xxx.pojo.UserAccount;
import com.xxx.service.PersonalDepartment.PersonalDepartmentService;
import com.xxx.service.PersonalDepartment.PersonalDepartmentServiceImpl;
import com.xxx.service.PersonalSignInRecord.SignInRecordService;
import com.xxx.service.PersonalSignInRecord.SignInRecordServiceImpl;
import com.xxx.utils.MyPageHelperUtils;
import com.xxx.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PersonalDepartment_informationAdd")
public class PersonalDepartment_informationAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalDepartment_informationAddServlet============");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String departmentId = UUIDUtils.getUUIDArg("department-");
        String departmentName = request.getParameter("departmentName");
        String departmentDesrciption = request.getParameter("departmentDesrciption");

        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(departmentName);
        department.setDepartmentDesrciption(departmentDesrciption);
        System.out.println(department.toString());

        PersonalDepartmentService personalDepartmentService = new PersonalDepartmentServiceImpl();
        personalDepartmentService.insert(department);


        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check","true");
        out.print(jsonObject);
        out.close();

    }
}
