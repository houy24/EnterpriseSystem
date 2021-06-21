package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addWorkTitle")
public class AddWorkTitleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("============进入 AddWorkTitleServlet============");


        // workTitleId 为随机生成的UUID，后台服务中生成
//        String workTitleId = request.getParameter("workTitleId");
        String workTitleName = request.getParameter("workTitleName");
        String workTitleMoneyStr = request.getParameter("workTitleMoney");

        Double workTitleMoney = Double.parseDouble(workTitleMoneyStr);

        System.out.println(null + "," + workTitleName + "," + workTitleMoney);

        WorkTitle workTitle = new WorkTitle(null,workTitleName,workTitleMoney);

        WorkTitleService workTitleService = new WorkTitleServiceImpl();

        // 无主建插入，业务层自动生成 UUID
        boolean flag = workTitleService.insertWorkTitleNoPrimaryKey(workTitle);

        if (flag == true) {
            System.out.println("添加成功！");
            // 返回true
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","true");
            out.print(jsonObject);
            out.close();
        } else if (flag == false){
            System.out.println("添加失败！");
            // 返回false
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","false");
            out.print(jsonObject);
            out.close();
        }

    }
}
