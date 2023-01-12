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
	public int updateExerRecord(ExerRecordVO vo) {
		// 오운완(나의운동기록) 수정
		return mapper.updateExerRecord(vo);
	}

	@Override
	public List<ExerRecordVO> ExerRecordList(String userId) {
		// 오운완(나의운동기록) 조회
		return mapper.ExerRecordList(userId);
	}

	@Override
	public List<ExerRecordVO> LatestExerRecord(String userId) {
		// 오운 유저의 운동 기록 중 가장 최신 데이터들 조회
		return mapper.LatestExerRecord(userId);
	}

	@Override
	public List<ExerRecordVO> DayRecordCounting(String userId) {
		// 최신 데이터에서 개수 카운팅
		return mapper.DayRecordCounting(userId);
	}

}
