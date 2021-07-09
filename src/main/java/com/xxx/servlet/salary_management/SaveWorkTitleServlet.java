package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.RoutineItem;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.RoutineItem.RoutineItemService;
import com.xxx.service.RoutineItem.RoutineItemServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveWorkTitle")
public class SaveWorkTitleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 SaveWorkTitleServlet============");

        String workTitleId = request.getParameter("workTitleId");
        String workTitleName = request.getParameter("workTitleName");
        String workTitleMoneyStr = request.getParameter("workTitleMoney");

        Double workTitleMoney = Double.parseDouble(workTitleMoneyStr);

        System.out.println(workTitleId + "," + workTitleName + "," + workTitleMoney);

        WorkTitle workTitle = new WorkTitle(workTitleId,workTitleName,workTitleMoney);

        WorkTitleService workTitleService = new WorkTitleServiceImpl();

        boolean flag = workTitleService.updateWorkTitle(workTitle);

        if (flag == true) {
            System.out.println("保存成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("保存失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
