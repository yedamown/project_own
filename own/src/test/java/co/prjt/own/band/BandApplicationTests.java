package co.prjt.own.band;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String test = "<p>ㅇㅇ</p><p><br></p><p><br></p><p><img src=\"/imgView/d2241bb4-37e5-454e-9caf-3d107e9cef0a_bandCreate01.png\" style=\"width: 25%;\" data-filename=\"d2241bb4-37e5-454e-9caf-3d107e9cef0a_bandCreate01.png\"><img src=\"/imgView/6bab4bf8-b082-46e1-8eba-aec577ac88c1_bandCreate01.png\" style=\"width: 25%;\" data-filename=\"6bab4bf8-b082-46e1-8eba-aec577ac88c1_bandCreate01.png\"><br></p>";
		// p태그(컨텐츠)에서 이미지를 추출하겠음
		List<String> pNewImgs = new ArrayList<String>();
		Pattern p = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher m = p.matcher(test);
        while (m.find()) {
            pNewImgs.add(m.group(1).substring(10));
        }
		
	}
}
