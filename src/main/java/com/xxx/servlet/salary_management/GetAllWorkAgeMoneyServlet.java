package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.Position;
import com.xxx.pojo.WorkAgeMoney;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyService;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllWorkAgeMoney")
public class GetAllWorkAgeMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllWorkAgeMoneyServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String workAgeStr = request.getParameter("workAge");
        System.out.println("workAge => " + workAgeStr);

        if (null == workAgeStr) {
            workAgeStr = "";
        }

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");

        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl();

        List<WorkAgeMoney> allWorkAgeMoney = workAgeMoneyService.getAllWorkAgeMoney();

        System.out.println(allWorkAgeMoney);
        for (int i = 0; i < allWorkAgeMoney.size(); i++) {
            if (!String.valueOf(allWorkAgeMoney.get(i).getWorkAge()).contains(workAgeStr)) {
                allWorkAgeMoney.remove(i);
                i--;
            }
        }
        System.out.println(allWorkAgeMoney);

        if (pageStr == null || limitStr == null) {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",allWorkAgeMoney.size());
            jsonObject.put("data",allWorkAgeMoney);
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
        jsonObject.put("count",allWorkAgeMoney.size());
        allWorkAgeMoney = MyPageHelperUtils.getListByPagesLimit(allWorkAgeMoney,page,limit);
        jsonObject.put("data",allWorkAgeMoney);
        out.print(jsonObject);
        out.close();
    }
}
