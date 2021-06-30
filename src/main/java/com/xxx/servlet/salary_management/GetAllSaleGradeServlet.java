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

@WebServlet("/getAllSaleGrade")
public class GetAllSaleGradeServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============进入 GetAllSaleGradeServlet============");
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

        SaleRecordService saleRecordService = new SaleRecordServiceImpl();  // 销售记录业务
        UserDataService userDataService = new UserDataServiceImpl();    // 用户信息业务
        DepartmentService departmentService = new DepartmentServiceImpl(); // 部门业务
        ProductService productService = new ProductServiceImpl(); // 产品业务
        ProductTypeService productTypeService = new ProductTypeServiceImpl(); // 产品类别业务

        List<SaleRecord> saleRecordList = saleRecordService.getSaleRecordByMonth(timeMonthSearch);

        // 过滤。。。

        // 权限过滤，如果是普通员工，不是管理员，则只能查看自己的情况
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if (userAccount.getUserType().equals(UserTypeUtils.UserType_Employee)) { // 员工
            UserData userData = (UserData) request.getSession().getAttribute("userData");
            userName = userData.getUserName(); // 当前员工
        }

        // 过滤用户，部门。。。
        for (int i = 0; i < saleRecordList.size(); i++) {
            // 当前过滤名字，和部门，名字模糊查询作为搜索条件
            UserData userData = userDataService.selectByUserId(saleRecordList.get(i).getUserId());

            if (!userData.getUserName().contains(userName) || !userData.getDepartmentId().contains(departmentIdSearch)) {
                saleRecordList.remove(i);
                i--;
            }
        }


        // 进行分页
        int allSize = saleRecordList.size();

        // 分页参数
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        // 分页处理
        if (null != pageStr && null != limitStr) {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            saleRecordList = MyPageHelperUtils.getListByPagesLimit(saleRecordList,page,limit);
        }
        System.out.println(saleRecordList);

        // 获取jsonobject list

        // 显示绩效信息，主要和工资有关
        // （日期，员工编号，员工姓名，员工部门）
        // （产品编号（隐藏），产品名称，产品类别， 产品类别编号（隐藏），
        // 销售总金额，提成，绩效工资）

        // SaleGrade
        // saleFinishTime , userId , userName , departmentName ,
        // productId（hidden），productName , productType，productTypeId（hidden）
        // saleSumMoney，productCommission，workResultMoney

        // json需要返回的list
        List<JSONObject> allSaleGrade = new ArrayList<>();

        for (SaleRecord saleRecord : saleRecordList) {
            // 表格主要属性
            JSONObject saleGrade = new JSONObject(); // SaleGrade 对象

            String thisTime = MyDateTimeUtils.getTimeByDate(saleRecord.getSaleFinishTime()); // 时间

            saleGrade.put("saleFinishTime",thisTime);
            saleGrade.put("userId",saleRecord.getUserId());

            UserData userData = userDataService.selectByUserId(saleRecord.getUserId());
            saleGrade.put("userName",userData.getUserName());

            Department department = departmentService.selectByDepartmentId(userData.getDepartmentId());

            saleGrade.put("departmentName",department.getDepartmentName());

            saleGrade.put("productId",saleRecord.getProductId());

            Product product = productService.getProductById(saleRecord.getProductId());

            saleGrade.put("productName",product.getProductName());
            saleGrade.put("productTypeId",product.getProductTypeId());

            ProductType productType = productTypeService.getProductTypeById(product.getProductTypeId());
            saleGrade.put("productType",productType.getProductTypeName());


            // saleSumMoney，productCommission，workResultMoney

            saleGrade.put("saleSumMoney",saleRecord.getSaleSumMoney());
            saleGrade.put("productCommission",productType.getProductCommission());

            // 总绩效等于 销售总金额 * 产品类别提成
            saleGrade.put("workResultMoney",saleRecord.getSaleSumMoney() * productType.getProductCommission());

            allSaleGrade.add(saleGrade);
        }

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","666");
        jsonObject.put("count",allSize);
        jsonObject.put("data",allSaleGrade);
        out.print(jsonObject);
        out.close();
        return;

    }
}
