package com.xxx.service.ProductWarehouseService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.dao.Product.ProductDao;
import com.xxx.dao.Product.ProductDaoImpl;
import com.xxx.dao.ProductType.ProductTypeDao;
import com.xxx.dao.ProductType.ProductTypeDaoImpl;
import com.xxx.dao.ProductWarehouse.ProductWarehouseDao;
import com.xxx.dao.ProductWarehouse.ProductWarehouseDaoImpl;
import com.xxx.pojo.Product;
import com.xxx.pojo.ProductType;
import com.xxx.pojo.ProductWarehouse;

import java.util.ArrayList;
import java.util.List;

public class ProductWarehouseServiceImpl implements ProductWarehouseService {
    private ProductWarehouseDao productWarehouseDao = new ProductWarehouseDaoImpl();

    @Override
    public List<ProductWarehouse> getAllProductWarehouse() {
        return productWarehouseDao.selectAll();
    }

    @Override
    public JSONArray toJSONArray(List<ProductWarehouse> list) {
        JSONArray jsonArray = new JSONArray();
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        for (ProductWarehouse p : list) {
            JSONObject object = new JSONObject();
            object.put("productWarehouseId", p.getProductWarehouseId());
            object.put("productId", p.getProductId());
            Product product = productDao.selectByPrimaryKey(p.getProductId());
            ProductType productType = productTypeDao.selectByPrimaryKey(product.getProductTypeId());
            object.put("productTypeId", productType.getProductTypeId());
            object.put("productTypeName", productType.getProductTypeName());
            object.put("productCommission", productType.getProductCommission());
            object.put("productName", p.getProductName());
            object.put("productNumber", p.getProductNumber());
            object.put("InWareHouseTime", p.getInWareHouseTime());
            object.put("productionAddress", p.getProductionAddress());
            object.put("oneInPrice", p.getOneInPrice());
            object.put("totalInPrice", p.getTotalInPrice());
            jsonArray.add(object);
        }
        return jsonArray;
    }

    @Override
    public List<ProductWarehouse> getByProductType(String productTypeName) {
        List<ProductWarehouse> productWarehouses = new ArrayList<>();
        ProductDao productDao = new ProductDaoImpl();
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl();

        ProductType productType = productTypeDao.selectByProductTypeName(productTypeName);
        List<Product> list = productDao.selectByProductTypeId(productType.getProductTypeId());

        for (int i = 0; i<list.size(); i++) {
            if (productWarehouseDao.selectAllByProductId(list.get(i).getProductId()) == null){
            }else {
                List<ProductWarehouse> productWarehouse = productWarehouseDao.selectAllByProductId(list.get(i).getProductId());
                productWarehouses.addAll(productWarehouse);
            }
        }
        return productWarehouses;
    }

    @Override
    public List<ProductWarehouse> getByProductName(String productName) {
        return productWarehouseDao.selectAllByProductName(productName);
    }

    @Override
    public boolean deleteByProductWarehouseId(String productWarehouseId) {
        int i = productWarehouseDao.deleteByPrimaryKey(productWarehouseId);
        if (i > 0)
            return true;
        return false;
    }

    @Override
    public boolean addOneInventory(ProductWarehouse productWarehouse) {
        int i = productWarehouseDao.insertSelective(productWarehouse);
        if (i > 0)
            return true;
        return false;
    }

    @Override
    public boolean updateProductNumber(ProductWarehouse productWarehouse) {
        return productWarehouseDao.updateByPrimaryKeySelective(productWarehouse)>0;
    }

    @Override
    public ProductWarehouse getByProductWarehouseId(String productWarehouseId) {
        return productWarehouseDao.selectByPrimaryKey(productWarehouseId);
    }

}
