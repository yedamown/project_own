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

}
