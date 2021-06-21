package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Position;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.WorkTitle.WorkTitleService;
import com.xxx.service.WorkTitle.WorkTitleServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllPosition")
public class GetAllPositionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllPositionServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String positionName = request.getParameter("positionName");
        System.out.println("positionName => " + positionName);

        if (null == positionName) {
            positionName = "";
        }

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");

        PositionService positionService = new PositionServiceImpl();
        List<Position> allPosition = positionService.getAllPosition();

        System.out.println(allPosition);
        for (int i = 0; i < allPosition.size(); i++) {
            if (!allPosition.get(i).getPositionName().contains(positionName)) {
                allPosition.remove(i);
                i--;
            }
        }
        System.out.println(allPosition);

        if (pageStr == null || limitStr == null) {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",allPosition.size());
            jsonObject.put("data",allPosition);
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
        jsonObject.put("count",allPosition.size());
        allPosition = MyPageHelperUtils.getListByPagesLimit(allPosition,page,limit);
        jsonObject.put("data",allPosition);
        out.print(jsonObject);
        out.close();
    }
}
