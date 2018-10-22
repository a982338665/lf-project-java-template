package pers.li.aseckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5转换工具类
 */
public class MD5Util {

	/**
	 * 单此MD5生成
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}

	/**
	 * 固定盐值申明
	 */
	private static final String salt = "1a2b3c4d";

	/**
	 * 第一次MD5：用户输入密码转换为表单密码-->反正明文密码传输
	 * @param inputPass
	 * @return
	 */
	public static String inputPassToFormPass(String inputPass) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
		System.out.println(str);
		return md5(str);
	}

	/**
	 * 第二次MD5：form密码转换为DB-->MD5
	 * @param formPass
	 * @param salt
	 * @return
	 */
	public static String formPassToDBPass(String formPass, String salt) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}


	/***
	 * 用户输入密码转换为DB密码，防止db泄露出现密码丢失
	 * @param inputPass
	 * @param saltDB
	 * @return
	 */
	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
		//d3b1294a61a07da9b49b6e22b2cbd7f9
//		System.out.println(inputPassToFormPass("123456"));
//		System.out.println(salt.charAt(5));
//		System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"));
//		System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
	}
	
}
