package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.*;
import com.xxx.service.Department.DepartmentService;
import com.xxx.service.Department.DepartmentServiceImpl;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyService;
import com.xxx.service.WorkAgeMoney.WorkAgeMoneyServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getAllUserDataMoney")
public class GetAllUserDataMoneyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllUserDataMoneyServlet============");
        //response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String userName = request.getParameter("userName");
        System.out.println("userName => " + userName);

        String departmentIdSearch = request.getParameter("departmentIdSearch");
        System.out.println("departmentIdSearch => " + departmentIdSearch);

        if (null == userName) {
            userName = "";
        }

        if (null == departmentIdSearch) {
            departmentIdSearch = "";
        }

        UserDataService userDataService = new UserDataServiceImpl(); // 用户信息业务
        DepartmentService departmentService = new DepartmentServiceImpl(); // 部门业务
        PositionService positionService = new PositionServiceImpl(); // 岗位业务
        WorkTitleService workTitleService = new WorkTitleServiceImpl(); // 职称业务
        WorkAgeMoneyService workAgeMoneyService = new WorkAgeMoneyServiceImpl(); // 工龄工资业务

        // 预处理，查询所有工龄工资水平
        List<WorkAgeMoney> allWorkAgeMoney = workAgeMoneyService.getAllWorkAgeMoney();

        List<UserData> allUserData = userDataService.selectAll(); // 所有用户的信息

        // ... 过滤

        // 搜索过滤
        for (int i = 0; i < allUserData.size(); i++) {
            // 当前暂时只过滤名字和部门，名字模糊查询作为搜索条件
            if (!allUserData.get(i).getUserName().contains(userName) || !allUserData.get(i).getDepartmentId().contains(departmentIdSearch)) {
                allUserData.remove(i);
                i--;
            }
        }

        int allSize = allUserData.size();

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        // 分页处理
        if (null != pageStr && null != limitStr) {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            allUserData = MyPageHelperUtils.getListByPagesLimit(allUserData,page,limit);
        }

        System.out.println(allUserData);

        List<JSONObject> allUserDataMoney = new ArrayList<>(); // json需要返回的list

        for (UserData userData : allUserData) {
            // 表格主要属性
            JSONObject userDataMoney = new JSONObject(); // userDataMoney 对象

            userDataMoney.put("userId",userData.getUserId());
            userDataMoney.put("userPhone",userData.getUserPhone());
            userDataMoney.put("userName",userData.getUserName());

            // 获取当前用户的部门，根据用户信息的部门编号
            Department department = departmentService.selectByDepartmentId(userData.getDepartmentId());
            userDataMoney.put("departmentName",department.getDepartmentName());

            // 获取当前用户的岗位，根据用户信息的岗位编号
            Position position = positionService.getPositionById(userData.getPositionId());
            userDataMoney.put("positionName",position.getPositionName());
            userDataMoney.put("positionMoney",position.getPositionMoney());

            // 获取当前用户的职称，根据用户信息的职称编号
            WorkTitle workTitle = workTitleService.getWorkTitleById(userData.getWorkTitleId());
            userDataMoney.put("workTitleName",workTitle.getWorkTitleName());
            userDataMoney.put("workTitleMoney",workTitle.getWorkTitleMoney());

            // 工龄和工龄工资
            double baseAgeMoney = 0; // 取小于等于当前工龄的最大基础工资
            for (WorkAgeMoney workAgeMoney : allWorkAgeMoney) {
                if (workAgeMoney.getWorkAge() <= userData.getWorkAge()) {
                    baseAgeMoney = Math.max(baseAgeMoney,workAgeMoney.getBaseAgeMoney());
                } else {       // （可能有问题）
                    break;
                }
            }
            userDataMoney.put("workAge",userData.getWorkAge());
            userDataMoney.put("baseAgeMoney",String.valueOf(baseAgeMoney));

            // 头像链接
            userDataMoney.put("userPhoto",userData.getUserPhoto());

            allUserDataMoney.add(userDataMoney);
        }

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allSize);
        jsonObject.put("data",allUserDataMoney);
        out.print(jsonObject);
        out.close();
        return;

    }
}
