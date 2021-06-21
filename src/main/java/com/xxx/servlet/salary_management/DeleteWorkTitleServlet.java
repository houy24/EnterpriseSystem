package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.RoutineItem;
import com.xxx.pojo.UserData;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.RoutineItem.RoutineItemService;
import com.xxx.service.RoutineItem.RoutineItemServiceImpl;
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

@WebServlet("/deleteWorkTitle")
public class DeleteWorkTitleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 DeleteWorkTitleServlet============");

        String workTitleId = request.getParameter("workTitleId");

        UserDataService userDataService = new UserDataServiceImpl();

        boolean f = userDataService.judgeWorkTitleExists(workTitleId);

        if (f == true) {
            System.out.println("该职称已被使用，不能删除！");
            // 返回 exists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","exists");
            out.print(jsonObject);
            out.close();
            return;
        }

        WorkTitleService workTitleService = new WorkTitleServiceImpl();

        boolean flag = workTitleService.deleteWorkTitle(workTitleId);

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
