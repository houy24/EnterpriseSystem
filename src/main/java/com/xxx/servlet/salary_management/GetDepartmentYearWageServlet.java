package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Department;
import com.xxx.service.Department.DepartmentService;
import com.xxx.service.Department.DepartmentServiceImpl;
import com.xxx.service.Wage.WageService;
import com.xxx.service.Wage.WageServiceImpl;
import com.xxx.utils.MyDateTimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getDepartmentYearWage")
public class GetDepartmentYearWageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetDepartmentYearWageServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        // 接收参数
        String timeYearSearch = request.getParameter("timeYearSearch");
        System.out.println("timeYearSearch => " + timeYearSearch);

        String nowYear = MyDateTimeUtils.getNowTime().substring(0,4);
        if (null == timeYearSearch || timeYearSearch.equals("")) {
            timeYearSearch = nowYear;
        }

        WageService wageService = new WageServiceImpl();

        DepartmentService departmentService = new DepartmentServiceImpl();

        List<Department> departmentList = departmentService.selectAll();


        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",departmentList.size());
        jsonObject.put("timeYearSearch",timeYearSearch);

        List<JSONObject> departmentWageList = new ArrayList<>();

        for (Department department : departmentList) {
            JSONObject departmentWage = new JSONObject();

            double value = wageService.getDepartmentYearWage(department.getDepartmentId(),timeYearSearch);

            departmentWage.put("value",String.format("%.2f",value));
            departmentWage.put("name",department.getDepartmentName());

            departmentWageList.add(departmentWage);
        }

        jsonObject.put("data_pie",departmentWageList);

        out.print(jsonObject);
        out.close();
    }
}
