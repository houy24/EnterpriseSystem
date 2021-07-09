package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyService;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveWorkAgeMoney")
public class SaveWorkAgeMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 SaveWorkAgeMoneyServlet============");

        String workAgeStr = request.getParameter("workAge");
        String baseAgeMoneyStr = request.getParameter("baseAgeMoney");
        String preWorkAgeStr = request.getParameter("preWorkAge");

        Integer workAge = Integer.parseInt(workAgeStr);
        Double baseAgeMoney = Double.parseDouble(baseAgeMoneyStr);
        Integer preWorkAge = Integer.parseInt(preWorkAgeStr);

        System.out.println(workAgeStr + "," + baseAgeMoneyStr + "," + preWorkAgeStr);

        WorkAgeMoney workAgeMoney = new WorkAgeMoney(workAge,baseAgeMoney);

        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl();

        if (!workAge.equals(preWorkAge) && null != workAgeMoneyService.getWorkAgeMoneyByWorkAge(workAge)) {
            System.out.println("该工龄已存在，保存失败！");
            // 返回 exists
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check","exists");
            out.print(jsonObject);
            out.close();
            return;
        }

        boolean flag = workAgeMoneyService.updateWorkAgeMoney(workAgeMoney,preWorkAge);

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
