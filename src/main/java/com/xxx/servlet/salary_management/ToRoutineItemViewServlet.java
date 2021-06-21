package com.xxx.servlet.salary_management;

import com.xxx.pojo.RoutineItem;
import com.xxx.service.RoutineItem.RoutineItemService;
import com.xxx.service.RoutineItem.RoutineItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ToRoutineItemView")
public class ToRoutineItemViewServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 ToRoutineItemViewServlet============");

        String routineItemId = "default";
        RoutineItemService routineItemService = new RoutineItemServiceImpl();

        RoutineItem routineItem = routineItemService.getRoutineItemById(routineItemId);
        request.setAttribute("routineItem",routineItem);
        System.out.println(routineItem);

        request.getRequestDispatcher("/pages/salary_management/RoutineItemView.jsp").forward(request,response);
    }
}
