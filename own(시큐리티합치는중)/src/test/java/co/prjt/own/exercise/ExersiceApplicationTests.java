package co.prjt.own.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordVO;

@SpringBootTest
public class ExersiceApplicationTests {
	@Autowired
	ExerRecordMapper mapper;

	@Test
	void contextLoads() {
		// 운동기록 인서트
//		ExerRecordVO vo = new ExerRecordVO();
//		vo.setUserId("nmj");
//		//vo.setExerecDate();
//		vo.setExersubNo("EXS_1");
//		vo.setExerecWeight(50);
//		vo.setExerecCondition("3");
//		vo.setExerecTime(65);
//		vo.setExerecMeter(1000);
//		vo.setExerecRoution("동네한바꾸");
//		vo.setExerecFeedback("열심히 하자");
//		mapper.insertExerRecord(vo);
		
		// 운동 기록 셀렉트
		mapper.ExerRecordList("nmj");
	}
}
