package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.SaleTask;
import com.xxx.pojo.UserAccount;
import com.xxx.service.SaleTaskService.SaleTaskService;
import com.xxx.service.SaleTaskService.SaleTaskServiceImpl;
import com.xxx.utils.MyPageHelperUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetAllSaleTaskServlet",urlPatterns = "/getAllSaleTask")
public class GetAllSaleTaskServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");

        System.out.println("============进入 GetAllSaleTaskServlet============");

        SaleTaskService service = new SaleTaskServiceImpl();
        List<SaleTask> saleTaskList = service.getAllSaleTasks();

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        int count = saleTaskList.size();
        if (page == null || limit == null) {
        } else {

            int page1 = Integer.parseInt(page);
            int limit1 = Integer.parseInt(limit);

            saleTaskList = MyPageHelperUtils.getListByPagesLimit(saleTaskList,page1,limit1);

        }
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = service.toJSONArray(saleTaskList);

        jsonObject.put("code",0);
        jsonObject.put("msg","销售任务清单");
        jsonObject.put("count",count);
        jsonObject.put("data",jsonArray);
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();

    }

}
