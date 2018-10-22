package pers.li.aseckill.vo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import pers.li.aseckill.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author:luofeng
 * @createTime : 2018/10/11 9:23
 * controller层数据传递
 */
@Data
@ToString
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;



}

