package com.xxx.service.WageContent;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.dao.Department.DepartmentDaoImpl;
import com.xxx.dao.Position.PositionDao;
import com.xxx.dao.Position.PositionDaoImpl;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.RoutineItem.RoutineItemDao;
import com.xxx.dao.RoutineItem.RoutineItemDaoImpl;
import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.TaxRate.TaxRateDao;
import com.xxx.dao.TaxRate.TaxRateDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.dao.Wage.WageDao;
import com.xxx.dao.Wage.WageDaoImpl;
import com.xxx.dao.WageContent.WageContentDao;
import com.xxx.dao.WageContent.WageContentDaoImpl;
import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDao;
import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDaoImpl;
import com.xxx.dao.WorkTitle.WorkTitleDao;
import com.xxx.dao.WorkTitle.WorkTitleDaoImpl;
import com.xxx.pojo.*;
import com.xxx.service.ProductType.ProductTypeService;
import com.xxx.service.ProductType.ProductTypeServiceImpl;
import com.xxx.service.SaleRecord.SaleRecordService;
import com.xxx.service.SaleRecord.SaleRecordServiceImpl;
import com.xxx.service.SignInRecord.SignInRecordService;
import com.xxx.service.SignInRecord.SignInRecordServiceImpl;
import com.xxx.service.TaxRate.TaxRateService;
import com.xxx.service.TaxRate.TaxRateServiceImpl;
import com.xxx.utils.MyDateTimeUtils;
import com.xxx.utils.UUIDUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class WageContentServiceImpl implements WageContentService {

    private WageContentDao wageContentDao = null;
    private WageDao wageDao = null;
    private UserDataDao userDataDao = null;
    private PositionDao positionDao = null;
    private SaleRecordService saleRecordService = null;
    private WorkAgeMoneyDao workAgeMoneyDao = null;
    private WorkTitleDao workTitleDao = null;
    private RoutineItemDao routineItemDao = null;
    private TaxRateService taxRateService = null;
    private ProductTypeService productTypeService = null;
    private SignInRecordService signInRecordService = null;
    private ProductDao productDao = null;


    public WageContentServiceImpl() {
        wageContentDao = new WageContentDaoImpl();
        wageDao = new WageDaoImpl();
        userDataDao = new UserDataDaoImpl();
        positionDao = new PositionDaoImpl();
        saleRecordService = new SaleRecordServiceImpl();
        workAgeMoneyDao = new WorkAgeMoneyDaoImpl();
        workTitleDao = new WorkTitleDaoImpl();
        routineItemDao = new RoutineItemDaoImpl();
        taxRateService = new TaxRateServiceImpl();
        productTypeService = new ProductTypeServiceImpl();
        signInRecordService = new SignInRecordServiceImpl();
        productDao = new ProductDaoImpl();
    }

    // 获取所有工资详情列表
    @Override
    public List<WageContent> getAllWageContent() {
        return wageContentDao.selectAll();
    }

    // 根据工资编号 获取工资详情
    @Override
    public WageContent getWageContentById(String wageId) {
        return wageContentDao.selectByPrimaryKey(wageId);
    }

    // 根据用户编号，获取工资详情列表
    @Override
    public List<WageContent> getWageContentByuserId(String userId) {
        return wageContentDao.selectAllByUserId(userId);
    }

    // 根据月份，获取工资详情列表
    @Override
    public List<WageContent> getWageContentByMonth(String timeMonthSearch) {
        return wageContentDao.getWageContentByMonth(timeMonthSearch);
    }

    // 根据月份，用户编号，获取该用户该月的工资详情
    @Override
    public WageContent getWageContentByUserIdAndMonth(String userId, String timeMonthSearch) {
        return wageContentDao.getWageContentByUserIdAndMonth(userId, timeMonthSearch);
    }

    // 判断某月工资是否结算
    @Override
    public boolean judgeExistWageContentByMonth(String timeMonthSearch) {
        int res = wageContentDao.getCountByMonth(timeMonthSearch);

        // 有记录，则已经结算
        return res > 0;
    }


    // 插入 一条 工资表 ，和 工资详情表
    @Override
    public boolean insertWageAndWageContent(WageContent wageContent) {
        // 根据 wageContent ， 创建对应的 wage 记录

        UserData userData = userDataDao.selectByPrimaryKey(wageContent.getUserId());

        Wage wage = new Wage(wageContent.getWageId(),wageContent.getUserId(),userData.getDepartmentId()
                            ,wageContent.getWageProvideTime(),wageContent.getRealyWage());

        int insert = wageContentDao.insert(wageContent);
        if (insert > 0) {   // 插入成功
            int insert1 = wageDao.insert(wage);

            if (insert1 > 0) {  // 插入成功
                return true;
            } else {    // 插入失败，回退
                wageContentDao.deleteByPrimaryKey(wageContent.getWageId());
                return false;
            }
        } else {    // 插入失败
            return false;
        }

    }

    // 删除一条 工资表 ， 和 工资详情表
    @Override
    public boolean deleteWageAndWageContentByWageId(String wageId) {

        Wage wage = wageDao.selectByPrimaryKey(wageId);
        WageContent wageContent = wageContentDao.selectByPrimaryKey(wageId);

        if (wage == null || wageContent == null) {
            return false;
        } else {
            wageDao.deleteByPrimaryKey(wageId);
            wageContentDao.deleteByPrimaryKey(wageId);
            return true;
        }

    }

    // 更新一条 工资表 ， 和 工资详情表
    @Override
    public boolean updateWageAndWageContent(WageContent wageContent) {

        int res1 = wageContentDao.updateByPrimaryKey(wageContent);

        // 根据 wageContent ， 创建对应的 wage 记录
        UserData userData = userDataDao.selectByPrimaryKey(wageContent.getUserId());
        Wage wage = new Wage(wageContent.getWageId(),wageContent.getUserId(),userData.getDepartmentId()
                ,wageContent.getWageProvideTime(),wageContent.getRealyWage());

        int res2 = wageDao.updateByPrimaryKey(wage);

        return res1 > 0 && res2 > 0;
    }

    // 计算某个用户当月工资详情
    @Override
    public WageContent calculateWageContentByUserIdAndMonth(String userId, String timeMonthSearch) throws ParseException {

        UserData userData = userDataDao.selectByPrimaryKey(userId);

        // 工资编号
        String wageId = UUIDUtils.getUUIDArg("wage-w");

        // 指定月份的28号时间，为结算时间
        String wageProvideTimeStr = timeMonthSearch + "-28 12:30:00";
        Date wageProvideTime = MyDateTimeUtils.getDateByTime(wageProvideTimeStr);

        Position position = positionDao.selectByPrimaryKey(userData.getPositionId());
        // 岗位工资
        double positionMoney = position.getPositionMoney();

        // 绩效工资
        double workResultMoney = 0;
        // 获取某个用户在某个月份的销售记录
        List<SaleRecord> saleRecordList = saleRecordService.getSaleRecordByUserIdAndMonth(userId, timeMonthSearch);
        for (SaleRecord saleRecord : saleRecordList) {
            Product product = productDao.selectByPrimaryKey(saleRecord.getProductId());
            ProductType productType = productTypeService.getProductTypeById(product.getProductTypeId());
            // 总绩效等于 销售总金额 * 产品类别提成
            workResultMoney += saleRecord.getSaleOneMoney() * productType.getProductCommission();
        }

        // 工龄工资
        double workAgeMoney = 0;
        List<WorkAgeMoney> workAgeMoneyList = workAgeMoneyDao.selectAll();
        for (WorkAgeMoney workAgeMoney1 : workAgeMoneyList) {
            if (workAgeMoney1.getWorkAge() <= userData.getWorkAge()) {
                workAgeMoney = Math.max(workAgeMoney,workAgeMoney1.getBaseAgeMoney());
            } else {
                break;
            }
        }
        workAgeMoney *= (double) userData.getWorkAge(); // 总工龄工资


        WorkTitle workTitle = workTitleDao.selectByPrimaryKey(userData.getWorkTitleId());
        // 职称工资
        double workTitleMoney = workTitle.getWorkTitleMoney();

        RoutineItem routineItem = routineItemDao.selectByPrimaryKey("default");
        // 餐饮补贴
        double eatAllowance = routineItem.getEatAllowance();
        // 交通补贴
        double carAllowance = routineItem.getCarAllowance();
        // 出差补贴
        double travelAllowance = 0; // 无出差工资
        // 住房补贴
        double houseAllowance = routineItem.getHouseAllowance();

        // 全勤工资
        double fullTimeMoney = 0;
        // 加班工资
        double workOverTimeMoney = 0;
        // 迟到罚金
        double lateMoney = 0;
        // 早退罚金
        double outEarlyMoney = 0;
        // 缺勤罚金
        double absentMoney = 0;

        // 考勤
        int limitsDays = MyDateTimeUtils.getLimitsDaysByMonth(timeMonthSearch); // 考勤记录统计到当月的哪天
        for (int day = 1; day <= limitsDays; day++) {
            String thisTime = timeMonthSearch + "-" + String.format("%02d",day);
            // 这天的考勤数据
            SignInRecord thisSignInRecord = signInRecordService
                    .getSignInRecordByDayAndUserId(thisTime,userId);

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
                        // 一次加班
                        workOverTimeMoney += routineItem.getWorkOvertimeAllowance();
                        System.out.println(thisTime + " 加班，共 " + workHours + " 小时");
                        continue;
                    } else {
                        // 工作时长不够 4 小时
                        continue;
                    }
                }
            } else {
                // 工作日
                if (thisSignInRecord == null) { // 工作日没上班，缺勤
                    // 缺勤
                    absentMoney -= routineItem.getAbsentPenalty();
                    System.out.println(thisTime + " 缺勤");
                    continue;
                }

                if (thisSignInRecord.getSignInTime() == null || thisSignInRecord.getSignInTime().equals("")) {
                    // 迟到
                    lateMoney -= routineItem.getLatePenalty();
                    System.out.println(thisTime + " 迟到，上班未签到");
                    continue;
                } else if (thisSignInRecord.getSignOutTime() == null || thisSignInRecord.getSignOutTime().equals("")) {
                    // 早退
                    outEarlyMoney -= routineItem.getOutEarlyPenalty();
                    System.out.println(thisTime + " 早退，下班未签退");
                    continue;
                }

                Date preDate = MyDateTimeUtils.getDateByTime(thisTime + " 09:00:00"); // 上班时间
                Date nxtDate = MyDateTimeUtils.getDateByTime(thisTime + " 18:00:00"); // 上班时间

                if (thisSignInRecord.getSignInTime().after(preDate)) { // 晚上班
                    // 迟到
                    lateMoney -= routineItem.getLatePenalty();
                    System.out.println(thisTime + " 迟到，上班未签到");
                    continue;
                }

                if (thisSignInRecord.getSignOutTime().before(nxtDate)) { // 早下班
                    // 早退
                    outEarlyMoney -= routineItem.getOutEarlyPenalty();
                    System.out.println(thisTime + " 早退，下班未签退");
                    continue;
                }

                // 完成工作，查看工作时长，     自己下班时间减去应当下班时间
                int workHours = MyDateTimeUtils.getDiffHours(nxtDate,thisSignInRecord.getSignOutTime());

                if (workHours >= 4) {
                    // 一次加班
                    workOverTimeMoney += routineItem.getWorkOvertimeAllowance();
                    System.out.println(thisTime + " 加班，共 " + workHours + " 小时");
                    continue;
                } else {
                    // 工作时长不够 4 小时
                    continue;
                }
            }
        }

        // 没有迟到，早退，缺勤，则算全勤
        if (lateMoney == 0 && outEarlyMoney == 0 && absentMoney == 0) {
            fullTimeMoney += routineItem.getFullTimeAllowance();
        }

        // 应得工资
        double shouldWage = positionMoney + workResultMoney + workAgeMoney +
                    fullTimeMoney + workTitleMoney + workOverTimeMoney +
                    eatAllowance + carAllowance + travelAllowance + houseAllowance +
                    lateMoney + outEarlyMoney + absentMoney;

        // 养老保险
        double oldEnsure = shouldWage * 0.008;
        // 医疗保险
        double medicalEnsure = shouldWage * 0.002 + 3.0;
        // 失业保险
        double lostJobEnsure = shouldWage * 0.002;
        // 工伤保险
        double workHurtEnsure = 0;
        // 生育保险
        double birthEnsure = 0;
        // 住房公积金
        double houseFundEnsure = shouldWage * 0.12;

        // 去除五险一金
        double realyWage = shouldWage - oldEnsure - medicalEnsure
                - lostJobEnsure - workHurtEnsure - birthEnsure - houseFundEnsure;

        // 计算税率
        // 个人所得税
        double oneSelfTax = 0;
        // 税收的起征点，默认 5000，可能起征点低于总工资，改变起征点
        double baseMoney = Math.min(5000.0,realyWage);
        // 减去起征点，不能减到0，最小0元
        realyWage = Math.max(0,realyWage - baseMoney);

        List<TaxRate> taxRateList = taxRateService.selectAll(); // 所有税率
        for(TaxRate taxRate : taxRateList) {
            if (realyWage <= taxRate.getMoney()) {
                oneSelfTax = realyWage * taxRate.getRate() - taxRate.getQuicklyReduce();
                break;
            }
        }
        realyWage += baseMoney; // 加回起征点工资

        realyWage = realyWage - oneSelfTax;

        // 工资详情
        WageContent wageContent = new WageContent(wageId,userId,wageProvideTime,
                    shouldWage,realyWage,positionMoney,workResultMoney,workAgeMoney,
                    fullTimeMoney,workTitleMoney,workOverTimeMoney,eatAllowance,carAllowance,
                    travelAllowance,houseAllowance,lateMoney,outEarlyMoney,absentMoney,oldEnsure,
                    medicalEnsure,lostJobEnsure,workHurtEnsure,birthEnsure,houseFundEnsure,oneSelfTax);

        return wageContent;
    }

}
