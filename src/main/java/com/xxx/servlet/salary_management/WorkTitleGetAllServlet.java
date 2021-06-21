package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.UserAccount;
import com.xxx.pojo.WorkTitle;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
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

@WebServlet("/getAllWorkTitle")
public class WorkTitleGetAllServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 WorkTitleGetAllServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String workTitleName = request.getParameter("workTitleName");
        System.out.println("workTitleName => " + workTitleName);

        if (null == workTitleName) {
            workTitleName = "";
        }

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");

        WorkTitleService workTitleService = new WorkTitleServiceImpl();
        List<WorkTitle> allWorkTitle = workTitleService.getAllWorkTitle();

        System.out.println(allWorkTitle);
        for (int i = 0; i < allWorkTitle.size(); i++) {
            if (!allWorkTitle.get(i).getWorkTitleName().contains(workTitleName)) {
                allWorkTitle.remove(i);
                i--;
            }
        }
        System.out.println(allWorkTitle);

        if (pageStr == null || limitStr == null) {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",allWorkTitle.size());
            jsonObject.put("data",allWorkTitle);
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
        jsonObject.put("count",allWorkTitle.size());
        allWorkTitle = MyPageHelperUtils.getListByPagesLimit(allWorkTitle,page,limit);
        jsonObject.put("data",allWorkTitle);
        out.print(jsonObject);
        out.close();
    }
}
