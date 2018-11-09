package pers.li.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * create by lishengbo 2018/11/8
 * 商品实体
 */
@Getter
@Setter
//@Entity
//@Table(name = "product")
public class Product {

    private Integer pid;
    private String pname;
    private Double price;

}
