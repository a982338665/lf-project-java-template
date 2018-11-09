package pers.li.service;

import org.springframework.transaction.annotation.Transactional;
import pers.li.dao.ProductDao;
import pers.li.entity.Product;

@Transactional
public class ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao){
        this.productDao = productDao;
    }

    public void save(Product product){
        productDao.save(product);
    }
}
