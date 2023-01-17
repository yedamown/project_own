package co.prjt.own.band;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.service.OwnLikeVO;

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
		List<String> nList = new ArrayList<String>();
		nList.add("BDD_30");
		nList.add("BDD_22");
		nList.add("BDD_24");
		System.out.println("11");
		List<OwnLikeVO> list = bandBoardDetailService.getOwnLike(nList, "hjj");
	}
}
