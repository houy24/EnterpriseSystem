package com.xxx.servlet;

import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/AddSalesRecord")
public class AddSalesRecordServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 AddSalesRecordServlet============");
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        UserDataDao userDataDao = new UserDataDaoImpl();
        UserData userData = userDataDao.selectByPrimaryKey(userAccount.getUserId());
        request.setAttribute("userData",userData);
        request.getRequestDispatcher("pages/sales_management/AddSalesRecord.jsp").forward(request,response);
    }
}
