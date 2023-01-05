package co.prjt.own;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "co.prjt.own.**.mapper")
public class OwnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnApplication.class, args);
	}

}
