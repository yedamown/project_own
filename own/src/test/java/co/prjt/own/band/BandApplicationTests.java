package co.prjt.own.band;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandCalendarDetailVO;
import co.prjt.own.band.service.BandMemberDefaultService;

@SpringBootTest
class BandApplicationTests {
	@Autowired
	BandMapper bandMapper;
	@Autowired
	BandMemberDefaultService bandMemberDefaultService;
	@Autowired
	BandBoardDetailService bandBoardDetailService;

	@Test
	void contextLoads() throws ParseException {
		BandCalendarDetailVO vo = BandCalendarDetailVO.builder()
									.bandAttend("BK01")
									.bandCalendarNo("19")
									.bandMemberNo("hjj")
									.build();
		bandBoardDetailService.updateCalendarDetail(vo);
	}
}
