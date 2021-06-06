package PoductTypeDaoTest;

import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.pojo.ProductType;
import org.junit.Test;

import java.util.List;

public class ProductTypeTest {
    @Test
    public void getAll(){
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
        List<ProductType> productTypes = productTypeDao.selectAll();
        System.out.println(productTypes);
    }
}
