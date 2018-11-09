package pers.li.service;


import pers.li.common.PageBean;
import pers.li.dao.DepartmentDao;
import pers.li.entity.Department;

import java.util.List;

public class DepartmentService{
    //注入Dao
    private DepartmentDao departmentDao;
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PageBean<Department> findByPage(Integer currPage) {
        PageBean<Department> pageBean = new PageBean<Department>();
        // 设置当前页数
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = departmentDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示的数据
        int begin = (currPage - 1) * pageSize;
        List<Department> list = departmentDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public void save(Department department) {
        departmentDao.save(department);
    }

    public Department findById(Integer did) {
        return departmentDao.findById(did);
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public void delete(Department department) {
        departmentDao.delete(department);
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
