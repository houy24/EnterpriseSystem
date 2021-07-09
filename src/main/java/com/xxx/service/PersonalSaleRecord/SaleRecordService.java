package com.xxx.service.PersonalSaleRecord;

import com.xxx.pojo.SaleRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SaleRecordService {

    //查询某员工销售记录总数
    int selectCountByUserId(@Param("userId")String userId);
    //查询销售记录
    List<SaleRecord> selectAllByUserId(String userId);
    //通过最大销售数量查询销售记录
    List<SaleRecord> selectAllByMaxNumber(@Param("userId")String userId,@Param("MaxNumber")String MaxNumber);
    //通过日期查询销售记录
    List<SaleRecord> selectAllByDate(@Param("userId")String userId,@Param("saleFinishTime") String saleFinishTime);
}
