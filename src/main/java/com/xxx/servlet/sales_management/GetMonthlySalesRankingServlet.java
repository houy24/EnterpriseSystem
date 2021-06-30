package com.xxx.servlet.sales_management;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.SaleRecord;
import com.xxx.pojo.UserData;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "GetMonthlySalesRankingServlet",urlPatterns = "/getMonthlySalesRanking")
public class GetMonthlySalesRankingServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 MonthlySalesRankingServlet============");
        UserDataDao userDataDao = new UserDataDaoImpl();
        SalesRecordService service1 = new SalesRecordServiceImpl();

        List<UserData> userDataList = userDataDao.selectAll();

        JSONArray jsonArray = new JSONArray();
        List<JSONObject> list = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        for (UserData u: userDataList) {
            JSONObject object = new JSONObject();
            List<SaleRecord> saleRecordList = service1.getPersonalSalesRecord(u.getUserId());

            double count = 0.0;
            List<JSONObject> array = new ArrayList<>();
            for (SaleRecord s: saleRecordList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("month",format.format(s.getSaleFinishTime()));
                jsonObject.put("saleRecord",s.getSaleSumMoney());
                array.add(jsonObject);
                count += s.getSaleSumMoney();
            }
            Double[] total = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

            for (JSONObject j: array) {
                if (j.getString("month").equals("2021-01")){
                    total[0] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-02")){
                    total[1] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-03")){
                    total[2] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-04")){
                    total[3] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-05")){
                    total[4] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-06")){
                    total[5] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-07")){
                    total[6] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-08")){
                    total[7] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-09")){
                    total[8] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-10")){
                    total[9] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-11")){
                    total[10] += j.getDouble("saleRecord");
                }else if (j.getString("month").equals("2021-12")){
                    total[11] += j.getDouble("saleRecord");
                }else {

                }
            }

            object.put("userId",u.getUserId());
            object.put("userName",u.getUserName());
            object.put("salesMoney",total);
            object.put("money",count);
            object.put("saleRecord",array);
            jsonArray.add(object);
            list.add(object);
        }

        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                double a = o1.getDouble("money");
                double b = o2.getDouble("money");
                if (a > b) {
                    return -1;
                } else if(a == b) {
                    return 0;
                } else
                    return 1;
            }
        });

        PrintWriter out = response.getWriter();
        out.print(list);
        out.close();
    }
}
