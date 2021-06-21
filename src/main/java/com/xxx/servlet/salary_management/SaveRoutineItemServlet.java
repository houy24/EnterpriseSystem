package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonString;
import com.xxx.pojo.RoutineItem;
import com.xxx.service.RoutineItem.RoutineItemService;
import com.xxx.service.RoutineItem.RoutineItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveRoutineItem")
public class SaveRoutineItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 SaveRoutineItemServlet============");

        String routineItemId = request.getParameter("routineItemId");
        Double latePenalty = Double.parseDouble(request.getParameter("latePenalty"));
        Double outEarlyPenalty = Double.parseDouble(request.getParameter("outEarlyPenalty"));
        Double absentPenalty = Double.parseDouble(request.getParameter("absentPenalty"));
        Double fullTimeAllowance = Double.parseDouble(request.getParameter("fullTimeAllowance"));
        Double workOvertimeAllowance = Double.parseDouble(request.getParameter("workOvertimeAllowance"));
        Double travelAllowance = Double.parseDouble(request.getParameter("travelAllowance"));
        Double eatAllowance = Double.parseDouble(request.getParameter("eatAllowance"));
        Double carAllowance = Double.parseDouble(request.getParameter("carAllowance"));
        Double houseAllowance = Double.parseDouble(request.getParameter("houseAllowance"));

        RoutineItem routineItem = new RoutineItem(routineItemId, latePenalty, outEarlyPenalty, absentPenalty,
                fullTimeAllowance, workOvertimeAllowance, travelAllowance, eatAllowance,
                carAllowance, houseAllowance);

        RoutineItemService routineItemService = new RoutineItemServiceImpl();

        boolean flag = routineItemService.updateRoutineItem(routineItem);

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
