package pers.li.service;


import pers.li.common.PageBean;
import pers.li.dao.EmployeeDao;
import pers.li.entity.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao;
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee login(Employee employee) {
        Employee existEmployee =employeeDao.findByUserNameAndPassword(employee);
        return existEmployee;
    }

    public PageBean<Employee> findByPage(Integer currPage) {
        PageBean<Employee> pageBean = new PageBean<Employee>();
        // 设置当前页数
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = employeeDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示的数据
        int begin = (currPage - 1) * pageSize;
        List<Employee> list = employeeDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public Employee findById(Integer eid) {
        return employeeDao.findById(eid);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }
}
