package ProductDaoTest;

import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;
import org.junit.Test;

import java.util.List;

public class ProductDaoTest {
    ProductDao productDao = new ProductDaoImpl();
    @Test
    public void getAllTest(){
        List<Product> products = productDao.selectAll();
        System.out.println(products);
    }
    @Test
    public void getById(){
        String id = "product-p001";
        Product product = productDao.selectByPrimaryKey(id);
        System.out.println(product);
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
        System.out.println(productTypeDao.selectByPrimaryKey(product.getProductTypeId()));
    }
}
