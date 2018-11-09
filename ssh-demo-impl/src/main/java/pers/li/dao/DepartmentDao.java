package pers.li.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import pers.li.entity.Department;

import java.util.List;

@Transactional
public class DepartmentDao extends HibernateDaoSupport {

    public List<Department> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
        List<Department> list = (List<Department>) this.getHibernateTemplate().findByCriteria(
                criteria, begin, pageSize);
        return list;
    }

    public int findCount() {
        String hql = "select count(*) from Department";
        List<?> list = this.getHibernateTemplate().find(hql);
        if (list.size() > 0) {
            return Integer.parseInt(list.get(0).toString());
        } else {
            return 0;
        }
    }

    public void save(Department department) {
        this.getHibernateTemplate().save(department);
    }

    public Department findById(Integer did) {
        Department department = this.getHibernateTemplate().get(Department.class, did);
        return department;
    }

    public void update(Department department) {
        HibernateTemplate template = this.getHibernateTemplate();
        System.out.println("DepartmentDaoImp:Update"+template);
        this.getHibernateTemplate().update(department);
    }

    public void delete(Department department) {
        this.getHibernateTemplate().delete(department);
    }

    public List<Department> findAll() {
       //String hql = "select * from Department";
        System.out.println("Dao层findAll被执行了！");
        String hql = " from Department";
        List<Department> list = (List<Department>) this.getHibernateTemplate().find(hql);
        System.out.println("Dao层findAll中的getHibernateTemple被执行了！");
        return list;
    }
}
