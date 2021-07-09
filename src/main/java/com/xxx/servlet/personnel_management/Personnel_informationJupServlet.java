package com.xxx.servlet.personnel_management;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Personnel_informationJup")
public class Personnel_informationJupServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 Personnel_informationJupServlet============");

        request.getRequestDispatcher("/pages/personnel_management/BackPersonnel_information.jsp").forward(request,response);

    }
}
