package pers.li.aseckill.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:23
 */
@Data
@ToString
public class Test  {

    private Integer id;

    private String name;

    private byte sex;

    public Test() {
    }

    public Test(Integer id, String name, byte sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
