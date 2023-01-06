package co.prjt.own.band;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandVO;

@SpringBootTest
class BandApplicationTests {
	@Autowired
	BandMapper bandMapper;
	
	@Test
	void contextLoads() {
//		BandVO vo = new BandVO();
//		vo.setBandNo("BDU_1");
//		System.out.println(vo.toString());
//		vo = bandMapper.getBand(vo);
//		System.out.println(vo.toString());
		List<Map<String, Object>> list = bandMapper.getBandAll(null);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
