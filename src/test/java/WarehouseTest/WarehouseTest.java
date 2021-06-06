package WarehouseTest;

import com.xxx.dao.ProductWarehouse.ProductWarehouseDao;
import com.xxx.dao.ProductWarehouse.ProductWarehouseDaoImpl;
import com.xxx.pojo.ProductWarehouse;
import org.junit.Test;

import java.util.List;

public class WarehouseTest {
    @Test
    public void getAllTest(){
        ProductWarehouseDao productWarehouseDao = new ProductWarehouseDaoImpl();
        List<ProductWarehouse> productWarehouses = productWarehouseDao.selectAll();
        System.out.println(productWarehouses);
    }
}