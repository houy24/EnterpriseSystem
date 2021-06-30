package com.xxx.servlet.sales_management;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MonthlySalesRankingServlet",urlPatterns = "/MonthlySalesRanking")
public class MonthlySalesRankingServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 MonthlySalesRankingServlet============");
        request.getRequestDispatcher("pages/sales_management/MonthlySalesRanking.jsp").forward(request,response);
    }

}
