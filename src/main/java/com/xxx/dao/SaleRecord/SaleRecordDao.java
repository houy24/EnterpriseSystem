package com.xxx.dao.SaleRecord;

import com.xxx.pojo.SaleRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleRecordDao {
    int deleteByPrimaryKey(String saleRecordId);

    int insert(SaleRecord record);

    int insertSelective(SaleRecord record);

    SaleRecord selectByPrimaryKey(String saleRecordId);

    List<SaleRecord> selectAllByUserId(String userId);

    List<SaleRecord> selectAll();

    int updateSaleRecordStateBySaleRecordId(@Param("saleRecordId") String saleRecordId ,@Param("saleRecordState") String saleRecordState);

    int updateByPrimaryKeySelective(SaleRecord record);

    int updateByPrimaryKey(SaleRecord record);

    List<SaleRecord> selectAllByUserIdAndTime(@Param("userId")String userId,@Param("startTime") String startTime, @Param("endTime") String endTime);

    //查询某员工销售记录总数
    int selectCountByUserId(String userId);
    //通过最大销售数量查询销售记录
    List<SaleRecord> selectAllByMaxNumber(@Param("userId")String userId,@Param("MaxNumber")String MaxNumber);
    //通过日期查询销售记录
    List<SaleRecord> selectAllByDate(@Param("userId")String userId,@Param("saleFinishTime") String saleFinishTime);
}