package co.prjt.own.band;

import java.text.ParseException;
import java.util.ArrayList;
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
		String test = "<p>ㅇㅇ</p><p><br></p><p><br></p><p><img src=\"/imgView/d2241bb4-37e5-454e-9caf-3d107e9cef0a_bandCreate01.png\" style=\"width: 25%;\" data-filename=\"d2241bb4-37e5-454e-9caf-3d107e9cef0a_bandCreate01.png\"><img src=\"/imgView/6bab4bf8-b082-46e1-8eba-aec577ac88c1_bandCreate01.png\" style=\"width: 25%;\" data-filename=\"6bab4bf8-b082-46e1-8eba-aec577ac88c1_bandCreate01.png\"><br></p>";
		// p태그(컨텐츠)에서 이미지를 추출하겠음
		String[] pImgs = test.split("<img src=\"/imgView/");
		for (int i = 0; i < pImgs.length; i++) {
			System.out.println(pImgs[i]);
		}
		List<String> pNewImgs = new ArrayList<String>();
//		//pimgs[0]은 공백임
		if (pImgs.length > 1) {
			for (int i = 1; i < pImgs.length; i++) {
				// 자르기..
				pImgs[i] = pImgs[i].substring(0, pImgs[i].indexOf("style=\""));
				System.out.println(pImgs[i]);
				pNewImgs.add(pImgs[i]);
			}
		}
	}
}
