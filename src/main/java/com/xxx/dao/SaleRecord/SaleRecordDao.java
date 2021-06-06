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

    int updateSaleRecordStateBySaleRecordId(@Param("saleRecordId") String saleRecordId ,@Param("saleRecordState") String saleRecordState);

    int updateByPrimaryKeySelective(SaleRecord record);

    int updateByPrimaryKey(SaleRecord record);
}