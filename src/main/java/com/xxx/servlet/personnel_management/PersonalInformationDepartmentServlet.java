package com.xxx.servlet.personnel_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Department;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;
import com.xxx.service.PersonalDepartment.PersonalDepartmentService;
import com.xxx.service.PersonalDepartment.PersonalDepartmentServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PersonalInformationDepartment")
public class PersonalInformationDepartmentServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 PersonalInformationDepartmentServlet============");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户名称"+userAccount);

        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        System.out.println("limit:" +limit+" "+"page:"+page);

        String department = request.getParameter("department");
        System.out.println("查询员工的部门："+department);

        PersonalDepartmentService personalDepartmentService = new PersonalDepartmentServiceImpl();
        Department departments = personalDepartmentService.selectAllByDepartmentName(department);
        System.out.println("部门编号："+departments.getDepartmentId());

        UserDataService userDataService = new UserDataServiceImpl();
        List<UserData> userDatas = userDataService.selectAllByDepartmentId(departments.getDepartmentId());
        System.out.println("查询的员工信息："+userDatas);

        userDatas = MyPageHelperUtils.getListByPagesLimit(userDatas,Integer.valueOf(page),Integer.valueOf(limit));
        System.out.println("分页后："+userDatas);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",userDatas.size());
        json.put("data",userDatas);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.close();
//        request.setAttribute("json",json);
//        request.getRequestDispatcher("/pages/personnel_management/BackPersonalSalesRecord.jsp").forward(request,response);

    }
}
