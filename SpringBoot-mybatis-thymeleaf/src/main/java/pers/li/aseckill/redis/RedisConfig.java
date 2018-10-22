package pers.li.aseckill.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//如果是application.properties，就不用写@PropertyScource("application.properties")，其他名字用写
///**@Value("${spring.redis.host}")*/配合使用在属性上面申明
//@PropertySource("classpath:application.properties")
//读取配置文件中前缀为spring.redis的所有数据并加载到此类中
//ignoreUnknownFields = false告诉Spring Boot在有属性不能匹配到声明的域的时候抛出异常
@ConfigurationProperties(
		prefix = "spring.redis"
	)
@PropertySource("classpath:application.properties")
@Data
public class RedisConfig {

	private String host;
	private int port;
	/**秒*/
	private int timeout;
	private String password;
	@Value("${spring.redis.pool.max-active}")
	private int poolMaxActive;
	@Value("${spring.redis.pool.max-idle}")
	private int poolMaxIdle;
	/**秒*/
	@Value("${spring.redis.pool.max-wait}")
	private int poolMaxWait;
	private int database;



}
