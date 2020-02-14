package pers.li.aseckill;

import org.common.anno.KNProjectDesc;
import org.common.anno.KNProjectName;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan({"org.common.*","pers.li.aseckill.dao"})
@ComponentScan(basePackages={"org.common.*","pers.li.*"})
@SpringBootApplication
@KNProjectName("项目名称-01")
@KNProjectDesc("项目描述-02")
public class ASeckillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ASeckillApplication.class, args);
	}


}
