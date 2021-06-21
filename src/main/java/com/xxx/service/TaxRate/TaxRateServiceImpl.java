package com.xxx.service.TaxRate;

import com.xxx.dao.TaxRate.TaxRateDao;
import com.xxx.dao.TaxRate.TaxRateDaoImpl;
import com.xxx.pojo.TaxRate;
import com.xxx.utils.UUIDUtils;

import java.util.Comparator;
import java.util.List;

public class TaxRateServiceImpl implements TaxRateService {

    private TaxRateDao taxRateDao = null;

    public TaxRateServiceImpl() {
        taxRateDao = new TaxRateDaoImpl();
    }

    @Override
    public List<TaxRate> selectAll() {
        return taxRateDao.selectAll();
    }

    @Override
    public TaxRate selectByTaxRateId(String taxRateId) {
        return taxRateDao.selectByPrimaryKey(taxRateId);
    }

    @Override
    public boolean updateTaxRate(TaxRate taxRate) {

        // 合法性校验
        List<TaxRate> taxRateList = taxRateDao.selectAll();

        for (int i = 0; i < taxRateList.size(); i++) {
            if (taxRateList.get(i).getTaxRateId().equals(taxRate.getTaxRateId())) {
                taxRateList.get(i).setMoney(taxRate.getMoney()); // 设置工资和税率
                taxRateList.get(i).setRate(taxRate.getRate());
                break;
            }
        }

        // 根据 工资档位 排序， 工资档位低的排前面
        taxRateList.sort(new Comparator<TaxRate>() {
            @Override
            public int compare(TaxRate taxRate1, TaxRate taxRate2) {

                return taxRate1.getMoney() == taxRate2.getMoney() ? 0
                        : taxRate1.getMoney() > taxRate2.getMoney() ? 1 : -1;
            }
        });

        boolean f = true;
        for (int i = 1; i < taxRateList.size(); i++) {
            // 前一个工资小的，税率更高，不对
            if (taxRateList.get(i-1).getRate() > taxRateList.get(i).getRate()) {
                f = false;
                break;
            }
        }

        if (f == false) {
            return false; // 修改失败
        }

        // 能够修改
        int res = taxRateDao.updateByPrimaryKey(taxRate);

        //System.out.println(" => update");

        if (res > 0) {
            //System.out.println(" => updateAllQuicklyReduce");
            this.updateAllQuicklyReduce();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTaxRate(String taxRateId) {
        int res = taxRateDao.deleteByPrimaryKey(taxRateId);

        if (res > 0) {
            this.updateAllQuicklyReduce();
            return true;
        }
        return false;
    }

    @Override
    public boolean insertTaxRate(TaxRate taxRate) {

        // 合法性校验
        List<TaxRate> taxRateList = taxRateDao.selectAll();

        if (taxRate.getMoney() > taxRateList.get(taxRateList.size()-1).getMoney()) {
            return false; // 超过上界，默认最后一条记录为上界，不能超过
        }

        taxRateList.add(taxRate);

        // 根据 工资档位 排序， 工资档位低的排前面
        taxRateList.sort(new Comparator<TaxRate>() {
            @Override
            public int compare(TaxRate taxRate1, TaxRate taxRate2) {

                return taxRate1.getMoney() == taxRate2.getMoney() ? 0
                        : taxRate1.getMoney() > taxRate2.getMoney() ? 1 : -1;
            }
        });

        boolean f = true;
        for (int i = 1; i < taxRateList.size(); i++) {
            // 前一个工资小的，税率更高，不对
            if (taxRateList.get(i-1).getRate() > taxRateList.get(i).getRate()) {
                f = false;
                break;
            }
        }

        if (f == false) {
            return false; // 修改失败
        }

        int res = taxRateDao.insert(taxRate);

        if (res > 0) {
            this.updateAllQuicklyReduce();
            return true;
        }
        return false;
    }

    @Override
    public boolean insertTaxRateNoPrimaryKey(TaxRate taxRate) {
        String taxRateId = UUIDUtils.getUUIDArg("taxRate-");
        taxRate.setTaxRateId(taxRateId);    // 插入主键
        return this.insertTaxRate(taxRate); // 调用有主键插入方法
    }

    // 更新所有税率的速算扣除数
    @Override
    public void updateAllQuicklyReduce() {
        // 所有税率
        List<TaxRate> taxRateList = taxRateDao.selectAll();

        // 更新所有税率的速算扣除数
        double pre = 0;
        taxRateList.get(0).setQuicklyReduce(pre);

        for (int i = 1; i < taxRateList.size(); i++) {
            pre = taxRateList.get(i-1).getMoney()
                    * ( taxRateList.get(i).getRate() - taxRateList.get(i-1).getRate() ) + pre;

            pre = Double.parseDouble(String.format("%.2f",pre));

            taxRateList.get(i).setQuicklyReduce(pre);
        }

        for (TaxRate taxRate : taxRateList) {
            taxRateDao.updateByPrimaryKey(taxRate); // 全部更新
        }

    }
}
