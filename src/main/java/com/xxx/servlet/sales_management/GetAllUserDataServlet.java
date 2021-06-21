package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllUserData")
public class GetAllUserDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        System.out.println("============进入 GetAllInventoryServlet============");
        UserDataDao userDataDao = new UserDataDaoImpl();
        List<UserData> userDataList = userDataDao.selectAll();

        JSONArray jsonArray = new JSONArray();
        for (UserData user: userDataList) {
            JSONObject object = new JSONObject();
            object.put("userId",user.getUserId());
            object.put("userName",user.getUserName());
            jsonArray.add(object);
        }

        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.close();
    }

}
