package pers.li.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 员工实体
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
public class Employee {

    private Integer eid;
    private String ename;
    private String sex;
    private Date birthday;
    private Date joinDate;
    /**
     * 工号
     */
    private String eno;
    private String username;
    private String password;
    private Department department;
}
