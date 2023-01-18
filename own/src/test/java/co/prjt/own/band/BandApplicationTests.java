package co.prjt.own.band;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
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
		BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		vo.setBandNo("BDU_1");
	}
}
