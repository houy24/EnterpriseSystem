package com.xxx.servlet.salary_management;

import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.*;
import com.xxx.service.Department.DepartmentService;
import com.xxx.service.Department.DepartmentServiceImpl;
import com.xxx.service.RoutineItem.RoutineItemService;
import com.xxx.service.RoutineItem.RoutineItemServiceImpl;
import com.xxx.service.SignInRecord.SignInRecordService;
import com.xxx.service.SignInRecord.SignInRecordServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
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
import java.util.*;

@WebServlet("/getAllSignManage")
public class GetAllSignManageServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllSignManageServlet============");
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
            timeMonthSearch = MyDateTimeUtils.getNowMonth(); // 当前月份
        }

        // 考勤记录统计到当月的哪天
        int limitsDays = MyDateTimeUtils.getLimitsDaysByMonth(timeMonthSearch);

        if (limitsDays == 0) { // 无数据
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("msg","666");
            jsonObject.put("count",0);
            jsonObject.put("data",null);
            out.print(jsonObject);
            out.close();
            return;
        }

        // 过滤。。。

        // 权限过滤，如果是普通员工，不是管理员，则只能查看自己的情况
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if (userAccount.getUserType().equals(UserTypeUtils.UserType_Employee)) { // 员工
            UserData userData = (UserData) request.getSession().getAttribute("userData");
            userName = userData.getUserName(); // 当前员工
        }


        SignInRecordService signInRecordService = new SignInRecordServiceImpl(); // 登录表，考勤业务
        UserDataService userDataService = new UserDataServiceImpl(); // 用户信息业务
        DepartmentService departmentService = new DepartmentServiceImpl(); // 部门业务

        List<UserData> userDataList = userDataService.selectAll();

        // 过滤用户，部门。。。
        for (int i = 0; i < userDataList.size(); i++) {
            // 当前过滤名字，名字模糊查询作为搜索条件
            if (!userDataList.get(i).getUserName().contains(userName)) {
                userDataList.remove(i);
                i--;
                continue;
            }
            // 过滤部门
            if (!userDataList.get(i).getDepartmentId().contains(departmentIdSearch)) {
                userDataList.remove(i);
                i--;
                continue;
            }
        }


        // 记录用户的部门名
        Map<String,String> userDepartmentName = new HashMap<String,String>();
        for (UserData userData : userDataList) {
            Department department = departmentService.selectByDepartmentId(userData.getDepartmentId());
            userDepartmentName.put(userData.getUserId(),department.getDepartmentName());
        }

        // 显示考勤信息，主要和工资有关
        // （日期<年-月-日>，员工编号，员工姓名，员工部门）
        // （考勤情况<迟到/早退/缺勤/出差/平常加班/周末加班>，工资情况（加200/减...））

        // SignManage
        // signDayTime , userId , userName , departmentName ,
        // signStatus , signMoneyStatus

        // json需要返回的list
        List<JSONObject> allSignManage = new ArrayList<>();

        // 日常工资项 //(!)
        String routineItemId = "default";
        RoutineItemService routineItemService = new RoutineItemServiceImpl();
        RoutineItem routineItem = routineItemService.getRoutineItemById(routineItemId);

        // 过滤完成，统计 1号 到 limitsDays ，所有用户的考勤信息
        for (int day = 1; day <= limitsDays; day++) {
            String thisTime = timeMonthSearch + "-" + String.format("%02d",day);

            for (UserData userData : userDataList) {
                // 统计当前用户 timeMonthSearch + "-" + day
                // 这天的考勤数据
                SignInRecord thisSignInRecord = signInRecordService
                        .getSignInRecordByDayAndUserId(thisTime,userData.getUserId());

                // 表格主要属性
                JSONObject signManage = new JSONObject(); // signManage 对象

                // signDayTime , userId , userName , departmentName ,
                // signStatus , signMoneyStatus

                signManage.put("signDayTime",thisTime);
                signManage.put("userId",userData.getUserId());
                signManage.put("userName",userData.getUserName());
                signManage.put("departmentName",userDepartmentName.get(userData.getUserId()));

                // 判断考勤情况，
                // （考勤情况<迟到/早退/缺勤/平常加班/周末加班>，工资情况（加200/减...））

                // 休息日，周六，周日
                // 工作日，周一到周五  09：00 ~ 18：00
                // 除去工作时间，多工作4小时以上，算当天加班，
                String weekDay = MyDateTimeUtils.getWeekday(thisTime);

                if (weekDay.equals("星期六") || weekDay.equals("星期日")) {
                    // 周末
                    if (thisSignInRecord == null) { // 周末没上班，正常
                        continue;
                    } else {
                        // 工作时长
                        if (thisSignInRecord.getSignInTime() == null || thisSignInRecord.getSignInTime().equals("")
                                || thisSignInRecord.getSignOutTime() == null || thisSignInRecord.getSignOutTime().equals("")) {
                            continue; // 没签到或签退，未完成工作
                        }
                        // 完成工作，查看工作时长
                        int workHours = MyDateTimeUtils.getDiffHours(thisSignInRecord.getSignInTime(),thisSignInRecord.getSignOutTime());

                        if (workHours >= 4) {
                            signManage.put("signStatus","加班，共 " + workHours + " 小时");
//                            signManage.put("signMoneyStatus","+200");
                            // 日常工资项的加班工资
                            signManage.put("signMoneyStatus","+" + routineItem.getWorkOvertimeAllowance());

                            allSignManage.add(signManage);
                            continue;
                        } else {
                            // 工作时长不够 4 小时
                            continue;
                        }
                    }

                } else {
                    // 工作日
                    if (thisSignInRecord == null) { // 工作日没上班，缺勤
                        signManage.put("signStatus","缺勤");
//                        signManage.put("signMoneyStatus","-200");
                        // 日常工资项的缺勤工资
                        signManage.put("signMoneyStatus","-" + routineItem.getAbsentPenalty());

                        allSignManage.add(signManage);
                        continue;
                    } else {

                        if (thisSignInRecord.getSignInTime() == null || thisSignInRecord.getSignInTime().equals("")) {
                            // 迟到
                            signManage.put("signStatus","迟到，上班未签到");
//                            signManage.put("signMoneyStatus","-100");
                            // 日常工资项的迟到工资
                            signManage.put("signMoneyStatus","-" + routineItem.getLatePenalty());

                            allSignManage.add(signManage);
                            continue;
                        } else if (thisSignInRecord.getSignOutTime() == null || thisSignInRecord.getSignOutTime().equals("")) {
                            // 早退
                            signManage.put("signStatus","早退，下班未签退");
//                            signManage.put("signMoneyStatus","-100");
                            // 日常工资项的早退工资
                            signManage.put("signMoneyStatus","-" + routineItem.getOutEarlyPenalty());

                            allSignManage.add(signManage);
                            continue;
                        }

                        Date preDate = MyDateTimeUtils.getDateByTime(thisTime + " 09:00:00"); // 上班时间
                        Date nxtDate = MyDateTimeUtils.getDateByTime(thisTime + " 18:00:00"); // 上班时间

                        if (thisSignInRecord.getSignInTime().after(preDate)) { // 晚上班
                            // 迟到
                            signManage.put("signStatus","迟到，" + MyDateTimeUtils.getTimeByDate(thisSignInRecord.getSignInTime()).substring(11));
//                            signManage.put("signMoneyStatus","-100");
                            // 日常工资项的迟到工资
                            signManage.put("signMoneyStatus","-" + routineItem.getLatePenalty());

                            allSignManage.add(signManage);
                            continue;
                        }

                        if (thisSignInRecord.getSignOutTime().before(nxtDate)) { // 早下班
                            // 早退
                            signManage.put("signStatus","早退，" + MyDateTimeUtils.getTimeByDate(thisSignInRecord.getSignOutTime()).substring(11));
//                            signManage.put("signMoneyStatus","-100");
                            // 日常工资项的早退工资
                            signManage.put("signMoneyStatus","-" + routineItem.getOutEarlyPenalty());
                            allSignManage.add(signManage);
                            continue;
                        }

                        // 完成工作，查看工作时长，     自己下班时间减去应当下班时间
                        int workHours = MyDateTimeUtils.getDiffHours(nxtDate,thisSignInRecord.getSignOutTime());

                        if (workHours >= 4) {
                            signManage.put("signStatus","加班，共 " + workHours + " 小时");
//                            signManage.put("signMoneyStatus","+200");
                            // 日常工资项的加班工资
                            signManage.put("signMoneyStatus","+" + routineItem.getWorkOvertimeAllowance() /*+ "元"*/);

                            allSignManage.add(signManage);
                            continue;
                        } else {
                            // 工作时长不够 4 小时
                            continue;
                        }

                    }
                }

            }
        }

        // 统计完考勤信息，在进行分页

        int allSize = allSignManage.size();

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        // 分页处理
        if (null != pageStr && null != limitStr) {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            allSignManage = MyPageHelperUtils.getListByPagesLimit(allSignManage,page,limit);
        }
        //System.out.println(allSignManage);

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allSize);
        jsonObject.put("data",allSignManage);
        out.print(jsonObject);
        out.close();
        return;

    }
}
