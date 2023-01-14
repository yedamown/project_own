package co.prjt.own;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = "co.prjt.own.**.mapper")
public class OwnApplication {
//주석테스트 수진수진
	public static void main(String[] args) {
		SpringApplication.run(OwnApplication.class, args);
	}
	
	
}