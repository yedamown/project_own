package co.prjt.own;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(locations = "classpath:/spring/*-context.xml")
class OwnApplicationTests {

	@Test
	void contextLoads() {
	}

}
