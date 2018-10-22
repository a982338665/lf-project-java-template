package pers.li.aseckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SUser {
	private Long id;
	private String nickname;
	private String password;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;
}
