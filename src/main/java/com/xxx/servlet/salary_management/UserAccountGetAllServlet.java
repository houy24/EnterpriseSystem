package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllUserAccount")
public class UserAccountGetAllServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 UserAccountGetAllServlet============");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");

        UserAccountService userAccountService = new UserAccountServiceImpl();
        List<UserAccount> allUserAccount = userAccountService.getAllUserAccount();

        if (pageStr == null || limitStr == null) {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",allUserAccount.size());
            jsonObject.put("data",allUserAccount);
            out.print(jsonObject);
            out.close();
            return;
        }

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        System.out.println(page + "," + limit);



        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allUserAccount.size());
        allUserAccount = MyPageHelperUtils.getListByPagesLimit(allUserAccount,page,limit);
        jsonObject.put("data",allUserAccount);
        out.print(jsonObject);
        out.close();

    }
}
