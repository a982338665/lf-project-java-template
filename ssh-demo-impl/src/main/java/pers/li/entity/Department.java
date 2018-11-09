package pers.li.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门实体类
 */
@Entity
@Table(name = "department")
@Getter
@Setter
@ToString
public class Department {

    private Integer did;
    private String dname;
    private String ddesc;
    /**
     * 部门员一对多
     */
    private Set<Employee> emplyoees = new HashSet<Employee>();

}
