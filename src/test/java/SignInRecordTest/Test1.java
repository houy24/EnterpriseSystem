package SignInRecordTest;

import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Department.DepartmentDao;
import com.xxx.dao.Department.DepartmentDaoImpl;
import com.xxx.dao.Position.PositionDao;
import com.xxx.dao.Position.PositionDaoImpl;
import com.xxx.dao.SaleTask.SaleTaskDao;
import com.xxx.dao.SaleTask.SaleTaskDaoImpl;
import com.xxx.dao.SignInRecord.SignInRecordDao;
import com.xxx.dao.SignInRecord.SignInRecordDaoImpl;
import com.xxx.dao.UserAccount.UserAccountMapper;
import com.xxx.dao.UserAccount.UserAccountMapperImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDao;
import com.xxx.dao.WorkAgeMoney.WorkAgeMoneyDaoImpl;
import com.xxx.dao.WorkTitle.WorkTitleDao;
import com.xxx.dao.WorkTitle.WorkTitleDaoImpl;
import com.xxx.pojo.*;
import com.xxx.service.UserAccount.UserAccountService;
import org.junit.Test;

import java.util.List;

public class Test1 {
    @Test
    public void test(){
        SignInRecordDao signInRecordDao = new SignInRecordDaoImpl();
        List<SignInRecord> signInRecords = signInRecordDao.selectAllByUserId("user-1");
        System.out.println(signInRecords);
    }
    @Test
    public void test2(){
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        List<Department> departments = departmentDao.selectAll();
        System.out.println(departments);
    }
    @Test
    public void test3(){
        PositionDao positionDao = new PositionDaoImpl();
        Position position = positionDao.selectByPrimaryKey("position-01");
        System.out.println(position);
    }

    @Test
    public void test4(){
        UserAccountMapper userAccountMapper = new UserAccountMapperImpl();
        UserDataDao userDataDao = new UserDataDaoImpl();
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        PositionDao positionDao = new PositionDaoImpl();
        WorkTitleDao workTitleDao = new WorkTitleDaoImpl();
        String userId = "user-1";
        UserAccount userAccount = userAccountMapper.getUserAccountByUserAccountId(userId);
        System.out.println(userAccount);
        UserData userData = userDataDao.selectByPrimaryKey(userAccount.getUserId());
        System.out.println(userData);
        Department department = departmentDao.selectByPrimaryKey(userData.getDepartmentId());
        System.out.println(department);
        Position position = positionDao.selectByPrimaryKey(userData.getPositionId());
        System.out.println(position);
        WorkTitle workTitle = workTitleDao.selectByPrimaryKey(userData.getWorkTitleId());
        System.out.println(workTitle);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userAccount.getUserId());
        jsonObject.put("userName",userData.getUserName());
        jsonObject.put("department",department.getDepartmentName());
        jsonObject.put("position",position.getPositionName());
        jsonObject.put("workTitle",workTitle.getWorkTitleName());
        System.out.println(jsonObject);
    }

    @Test
    public void test5(){
        WorkAgeMoneyDao workAgeMoneyDao = new WorkAgeMoneyDaoImpl();
        List<WorkAgeMoney> workAgeMoney = workAgeMoneyDao.selectAll();
        System.out.println(workAgeMoney);
    }

    @Test
    public void test6(){
        SaleTaskDao saleTaskDao = new SaleTaskDaoImpl();
        List<SaleTask> saleTask = saleTaskDao.selectAllByUserId("user-1");
        System.out.println(saleTask);
    }
}
