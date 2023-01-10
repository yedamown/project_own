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
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandVO;

@SpringBootTest
class BandApplicationTests {
	@Autowired
	BandMapper bandMapper;
	@Autowired
	BandMemberDefaultService bandMemberDefaultService;
	@Test
	void contextLoads() throws ParseException {
//		System.out.println(bandMapper.allExcersie());
//		System.out.println(bandMapper.allLocation());
		System.out.println(bandMemberDefaultService.getBandMemberDefault("hjj"));
//		BandVO vo = new BandVO();
//		List<String> bandNos = new ArrayList<String>();
//		bandNos.add("BDU_1");
//		bandNos.add("BDU_2");
//		bandNos.add("BDU_3");
//		vo.setBandNos(bandNos);
//		List<Map<String, Object>> list = bandMapper.threeBand(vo);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		vo.setBandLeaderid("hjj");
//		vo.setBandNo("BDU_1");
//		System.out.println(vo.toString());
//		vo = bandMapper.getBand(vo);
//		System.out.println(vo.toString());
//		BandMemberDetailVO vo = new BandMemberDetailVO();
//		vo.setUserId("hjj");
//		List<BandVO> list = bandMapper.getMemberBandAll(vo);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		List<Map<String, Object>> list = bandMapper.getBandRecentAll(vo);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		//밴드 검색...페이지 정보는 ajax로 받아오기
//		Paging paging = new Paging();
//		paging.setPageUnit(3);
//		System.out.println(vo.toString());
//		BandVO vo2 = new BandVO();
//		vo2.setBandLeaderid("hjj");
//		System.out.println(bandMapper.count2(vo));
//		//페이지
//		paging.setTotalRecord(bandMapper.count2(vo));
//		vo2.setFirst(paging.getFirst());
//		System.out.println(vo2.getFirst());
//		vo2.setLast(paging.getLast());
//		System.out.println(vo2.getLast());
//		List<Map<String, Object>> list = bandMapper.getMyBandAll(vo2);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		//한문장으로 된 번호를 보냄..ex)BDU_17BDU_15BDU_14 
		BandVO vo = new BandVO();
		vo.setBandLeaderid("hjj");
		//관심종목
		vo.setBandKeyword("EXS_6");
		//관심지역
		vo.setBandLocation("LC06");
		//생일을 담음
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "1993-03-04";
        Date date = new Date(sdf.parse(strDate).getTime());
        System.out.println(date);
		vo.setBandAgeAfteroption(date);
		//성별
		vo.setBandGenderOption("GD01");
		List<BandVO> list = bandMapper.recomBand(vo);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
