package pers.li.aseckill.redis.service;

import pers.li.aseckill.redis.BasePrefix;

public class SUserKey extends BasePrefix {

	//有效期两天
	public static final int TOKEN_EXPIRE = 3600*24 * 2;

	private SUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static SUserKey token = new SUserKey(TOKEN_EXPIRE, "tk");
	/**
	 * 对象缓存，如果不做修改，则永久有效，故不设有效期
	 */
	public static SUserKey getById = new SUserKey(0, "id");
}
