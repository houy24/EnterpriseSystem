package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.*;
import com.xxx.service.Department.DepartmentService;
import com.xxx.service.Department.DepartmentServiceImpl;
import com.xxx.service.Product.ProductService;
import com.xxx.service.Product.ProductServiceImpl;
import com.xxx.service.ProductType.ProductTypeService;
import com.xxx.service.ProductType.ProductTypeServiceImpl;
import com.xxx.service.SaleRecord.SaleRecordService;
import com.xxx.service.SaleRecord.SaleRecordServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WageContent.WageContentService;
import com.xxx.service.WageContent.WageContentServiceImpl;
import com.xxx.utils.MyDateTimeUtils;
import com.xxx.utils.MyPageHelperUtils;
import com.xxx.utils.UserTypeUtils;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getAllWageAll")
public class GetAllWageAllServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllWageAllServlet============");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        // 接受搜索参数
        String userName = request.getParameter("userName");
        String departmentIdSearch = request.getParameter("departmentIdSearch");
        String timeMonthSearch = request.getParameter("timeMonthSearch");
        System.out.println(userName + "," + departmentIdSearch + "," + timeMonthSearch );

        if (null == userName) {
            userName = "";
        }
        if (null == departmentIdSearch) {
            departmentIdSearch = "";
        }
        if (null == timeMonthSearch || timeMonthSearch.equals("")) {
            timeMonthSearch = ""; // 默认查看所有
        }

        WageContentService wageContentService = new WageContentServiceImpl();
        UserDataService userDataService = new UserDataServiceImpl();    // 用户信息业务
        DepartmentService departmentService = new DepartmentServiceImpl(); // 部门业务

        // 过滤。。。

        // 权限过滤，如果是普通员工，不是管理员，则只能查看自己的情况
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if (userAccount.getUserType().equals(UserTypeUtils.UserType_Employee)) { // 员工
            UserData userData = (UserData) request.getSession().getAttribute("userData");
            userName = userData.getUserName(); // 当前员工
        }

        // 过滤日期
        List<WageContent> wageContentList = null;

        if (timeMonthSearch.equals("")) { // 查询所有
            wageContentList = wageContentService.getAllWageContent();
        } else {    // 根据月份查询
            wageContentList = wageContentService.getWageContentByMonth(timeMonthSearch);
        }

        // 过滤用户，部门
        for (int i = 0; i < wageContentList.size(); i++) {
            // 当前过滤名字，和部门，名字模糊查询作为搜索条件
            UserData userData = userDataService.selectByUserId(wageContentList.get(i).getUserId());
            if (!userData.getUserName().contains(userName) || !userData.getDepartmentId().contains(departmentIdSearch)) {
                wageContentList.remove(i);
                i--;
            }
        }

        // 进行分页
        int allSize = wageContentList.size();

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        // 分页处理
        if (null != pageStr && null != limitStr) {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            wageContentList = MyPageHelperUtils.getListByPagesLimit(wageContentList,page,limit);
        }
        System.out.println(wageContentList);

        // 获取jsonobject list

        // wageAll
        //月份   员工姓名
        // 员工编号  员工部门 （员工信息）      应发工资  实发工资 （工资情况）
        //岗位工资  绩效工资  职称工资 	（基本工资）	 工龄工资 加班工资  全勤工资  （奖金）
        //餐饮补贴  交通补贴  出差补贴  住房补贴   （补贴）  迟到罚金  早退罚金  缺勤罚金   （缺勤罚金）
        //养老保险  医疗保险  失业保险  工伤保险  生育保险  住房公积金   （五险一金）
        //个人所得税   （纳税）

        //wageProvideTime   userName       userId  departmentName     shouldWage  realyWage
        //positionMoney  workResultMoney workTitleMoney  	workAgeMoney workOverTimeMoney  fullTimeMoney
        //eatAllowance carAllowance	travelAllowance houseAllowance 	lateMoney outEarlyMoney absentMoney
        //oldEnsure medicalEnsure lostJobEnsure workHurtEnsure birthEnsure houseFundEnsure
        //oneSelfTax

        // json需要返回的list
        List<JSONObject> allWageAll = new ArrayList<>();

        for (WageContent wageContent : wageContentList) {
            // 表格主要属性
            JSONObject wageAll = new JSONObject(); // WageAll 对象

            String nowMonth = MyDateTimeUtils.getTimeByDate(wageContent.getWageProvideTime()).substring(0,7);
            wageAll.put("wageProvideTime",nowMonth); // 月份

            UserData userData = userDataService.selectByUserId(wageContent.getUserId());
            wageAll.put("userName",userData.getUserName()); // 员工姓名

            // 员工信息
            wageAll.put("userId",wageContent.getUserId()); // 员工编号

            Department department = departmentService.selectByDepartmentId(userData.getDepartmentId());
            wageAll.put("departmentName",department.getDepartmentName()); // 员工部门

            // 工资情况
            wageAll.put("shouldWage",wageContent.getShouldWage());  // 应发工资
            wageAll.put("realyWage",wageContent.getRealyWage());   // 实发工资

            // 基本工资
            wageAll.put("positionMoney",wageContent.getPositionMoney());   // 岗位工资
            wageAll.put("workResultMoney",wageContent.getWorkResultMoney()); // 绩效工资
            wageAll.put("workTitleMoney",wageContent.getWorkTitleMoney());  // 职称工资

            // 奖金
            wageAll.put("workAgeMoney",wageContent.getWorkAgeMoney());    // 工龄工资
            wageAll.put("workOverTimeMoney",wageContent.getWorkOverTimeMoney());   // 加班工资
            wageAll.put("fullTimeMoney",wageContent.getFullTimeMoney());   // 全勤工资

            // 补贴
            wageAll.put("eatAllowance",wageContent.getEatAllowance());    // 餐饮补贴
            wageAll.put("carAllowance",wageContent.getCarAllowance());    // 交通补贴
            wageAll.put("travelAllowance",wageContent.getTravelAllowance()); // 出差补贴
            wageAll.put("houseAllowance",wageContent.getHouseAllowance());  // 住房补贴

            // 缺勤罚金
            wageAll.put("lateMoney",wageContent.getLateMoney());   // 迟到罚金
            wageAll.put("outEarlyMoney",wageContent.getOutEarlyMoney());   // 早退罚金
            wageAll.put("absentMoney",wageContent.getAbsentMoney()); // 缺勤罚金

            // 五险一金
            wageAll.put("oldEnsure",wageContent.getOldEnsure());   // 养老保险
            wageAll.put("medicalEnsure",wageContent.getMedicalEnsure());   // 医疗保险
            wageAll.put("lostJobEnsure",wageContent.getLostJobEnsure());   // 失业保险
            wageAll.put("workHurtEnsure",wageContent.getWorkHurtEnsure());  // 工伤保险
            wageAll.put("birthEnsure",wageContent.getBirthEnsure());     // 生育保险
            wageAll.put("houseFundEnsure",wageContent.getHouseFundEnsure()); // 住房公积金

            // 纳税
            wageAll.put("oneSelfTax",wageContent.getOneSelfTax()); // 个人所得税

            allWageAll.add(wageAll);
        }

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allSize);
        jsonObject.put("data",allWageAll);
        out.print(jsonObject);
        out.close();
        return;

    }
}
