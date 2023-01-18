package co.prjt.own.exercise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordService;
import co.prjt.own.exercise.service.ExerRecordVO;

public class ExerRecordServiceImpl implements ExerRecordService{
	@Autowired
	ExerRecordMapper mapper;
	
	@Override
	public int insertExerRecord(ExerRecordVO vo) {
		// 오운완(나의운동기록) 등록
		return mapper.insertExerRecord(vo);
	}

	@Override
	public List<ExerRecordVO> LatestExerRecord(String userId) {
		// 오운 유저의 운동 기록 중 가장 최신 데이터들 조회
		return mapper.LatestExerRecord(userId);
	}

	@Override
	public List<ExerRecordVO> dayRecordCounting(String userId) {
		// 최신 데이터에서 개수 카운팅
		return mapper.dayRecordCounting(userId);
	}

	@Override
	public List<ExerRecordVO> getWeight(String userId) {
		// 데이터 중 최근 7일 날짜와 몸무게 출력
		return mapper.getWeight(userId);
	}
	
	@Override
	public List<ExerRecordVO> selectRecord(ExerRecordVO vo) {
		// 기간 설정해서 운동 데이터 조회
		return mapper.selectRecord(vo);
	}
	
	@Override
	public List<ExerRecordVO> selectWeight(ExerRecordVO vo) {
		// 기간 설정해서 운동 데이터 조회
		return mapper.selectWeight(vo);
	}

	@Override
	public List<ExerRecordVO> selectCounting(ExerRecordVO vo) {
		// 기간 설정해서 운동 카운팅
		return mapper.selectCounting(vo);
	}

}
