package com.xxx.service.TaxRate;

import com.xxx.pojo.TaxRate;

import java.util.List;

public interface TaxRateService {

    List<TaxRate> selectAll();

    TaxRate selectByTaxRateId(String taxRateId);

    boolean updateTaxRate(TaxRate taxRate);

    boolean deleteTaxRate(String taxRateId);

    // 有主键
    boolean insertTaxRate(TaxRate taxRate);

    // 无主键，此处生成 UUID
    boolean insertTaxRateNoPrimaryKey(TaxRate taxRate);

    // 更新全部数据的速算扣除数
    void updateAllQuicklyReduce();

}
