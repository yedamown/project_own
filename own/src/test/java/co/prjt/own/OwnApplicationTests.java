package co.prjt.own;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j;

@SpringBootTest
@ContextConfiguration(locations = "classpath:/spring/*-context.xml")
class OwnApplicationTests {

	@Test
	void contextLoads() {
	}

}
