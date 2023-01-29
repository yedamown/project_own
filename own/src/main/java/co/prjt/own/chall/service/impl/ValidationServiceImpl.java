package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ValidationMapper;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.chall.service.ValidationService;
import co.prjt.own.chall.service.ValidationVO;

@Component
public class ValidationServiceImpl implements ValidationService{

	@Autowired ValidationMapper mapper;
	
	@Override
	public int insertVld(ValidationVO vo) {
		return mapper.insertVld(vo);
	}

	@Override
	public int updateVld(ValidationVO vo) {
		return mapper.updateVld(vo);
	}

	@Override
	public ValidationVO getVld(ValidationVO vo) {
		return mapper.getVld(vo);
	}

	@Override
	public List<ValidationVO> getChallVld(ValidationVO vo) {
		return mapper.getChallVld(vo);
	}

	@Override
	public List<ValidationVO> getMyVld(ValidationVO vo) {
		return mapper.getMyVld(vo);
	}

	@Override
	public List<ValidationVO> getMyChallVld(ValidationVO vo) {
		return mapper.getMyChallVld(vo);
	}

	//주에 인증 횟수 가져오기
	@Override
	public int countWeekVld(ValidationVO vo) {
		int day = mapper.startToToday(vo);
		System.out.println("=== 가져온 vo"+vo);
		int before = (int) Math.floor(day/7);
		int now = before + 1;
		System.out.println("============= 시작날짜와 비교" + day);
		System.out.println("============= 전 주" + before);
		System.out.println("============= 이번 주" + now);
		vo.setBeforeWeek(before);
		vo.setNowWeek(now);
		System.out.println("=== 설정해서 이제 날짜검색 "+vo);
		int rs = mapper.countWeekVld(vo);
		return rs;
	}
	
	// 총 인증횟수
	@Override
	public int countVld(ValidationVO vo) {
		return mapper.countVld(vo);
	}
	
	//오늘 인증 횟수
	@Override
	public int todayVld(ValidationVO vo) {
		return mapper.todayVld(vo);
	}

	//도전멤버 인증 평균
	@Override
	public int memVldAvg(ValidationVO vo) {
		return mapper.memVldAvg(vo);
	}

}
