package pers.li.dao;


import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import pers.li.entity.Product;

/**
 * Spring给Hiber自带了一个注入模板HibernateDaoSupport
 */
public class ProductDao extends HibernateDaoSupport {

    public void save(Product product) {
        this.getHibernateTemplate().save(product);
        //this.getSessionFactory().getCurrentSession().save(product);
    }
}
