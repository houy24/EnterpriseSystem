package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Position;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addPosition")
public class AddPositionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("============进入 AddPositionServlet============");

        // positionId 为随机生成的UUID，后台服务中生成
        String positionName = request.getParameter("positionName");
        String positionMoneyStr = request.getParameter("positionMoney");
        String positionDesrciption = request.getParameter("positionDesrciption");

        Double positionMoney = Double.parseDouble(positionMoneyStr);

        System.out.println(null + "," + positionName + "," + positionMoneyStr + "," + positionDesrciption);

        Position position = new Position(null,positionName,positionMoney,positionDesrciption);

        PositionService positionService = new PositionServiceImpl();

        // 无主建插入，业务层自动生成 UUID
        boolean flag = positionService.insertPositionNoPrimaryKey(position);

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
