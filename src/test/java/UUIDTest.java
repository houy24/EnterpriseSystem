import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.SaleRecord;
import com.xxx.pojo.UserData;
import com.xxx.service.SalesRecordService.SalesRecordService;
import com.xxx.service.SalesRecordService.SalesRecordServiceImpl;
import com.xxx.utils.UUIDUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UUIDTest {

    @Test
    public void uuidTest() {
        System.out.println(UUIDUtils.getUUID());
        System.out.println(UUIDUtils.getUUID_());
    }
    @Test
    public void test() throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM");
        SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();
        List<SaleRecord> saleRecords = saleRecordDao.selectAll();
        System.out.println(format1.format(saleRecords.get(0).getSaleFinishTime()));
    }
    @Test
    public void fun(){
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
            System.out.println(array);
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
            object.put("saleRecord",array);
            jsonArray.add(object);
            list.add(object);
        }

    }
}
