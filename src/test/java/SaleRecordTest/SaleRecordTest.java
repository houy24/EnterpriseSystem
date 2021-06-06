package SaleRecordTest;

import com.xxx.dao.SaleRecord.SaleRecordDao;
import com.xxx.dao.SaleRecord.SaleRecordDaoImpl;
import com.xxx.pojo.SaleRecord;
import org.junit.Test;

import java.util.List;

public class SaleRecordTest {
    @Test
    public void getTest(){
        String userId = "user-1";
        SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();
        List<SaleRecord> saleRecords = saleRecordDao.selectAllByUserId(userId);
        System.out.println(saleRecords);
        String saleRecordState = "可见";
        String saleRecordId = saleRecords.get(0).getSaleRecordId();
        int i = saleRecordDao.updateSaleRecordStateBySaleRecordId(saleRecordId, saleRecordState);
        System.out.println(saleRecordDao.selectByPrimaryKey(saleRecordId));
    }
}
