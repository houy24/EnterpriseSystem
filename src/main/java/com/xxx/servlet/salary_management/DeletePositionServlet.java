package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletePosition")
public class DeletePositionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 DeletePositionServlet============");

        String positionId = request.getParameter("positionId");

        UserDataService userDataService = new UserDataServiceImpl();

        boolean f = userDataService.judgePositionExists(positionId);

        if (f == true) {
            System.out.println("该岗位已被使用，不能删除！");
            // 返回 exists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","exists");
            out.print(jsonObject);
            out.close();
            return;
        }

        PositionService positionService = new PositionServiceImpl();

        boolean flag = positionService.deletePosition(positionId);

        if (flag == true) {
            System.out.println("删除成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("删除失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
