package co.prjt.own;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.prjt.own.band.service.BandBoardDetailService;

@SpringBootTest
@ContextConfiguration(locations = "classpath:/spring/*-context.xml")
class OwnApplicationTests {
	@Autowired BandBoardDetailService bandBoardDetailService;
	@Test
	void contextLoads() {
		bandBoardDetailService.selectCalendarNow("BDU_1", "current_date");
	}

}
