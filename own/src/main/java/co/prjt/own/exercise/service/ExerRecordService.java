package co.prjt.own.exercise.service;

import java.util.List;

public interface ExerRecordService {
	// 오운완(나의운동기록) 등록
	int insertExerRecord(ExerRecordVO vo);

	// 오운완(나의운동기록) 수정
	int updateExerRecord(ExerRecordVO vo);
	
	// 오운완(나의운동기록) 조회
	List<ExerRecordVO> ExerRecordList(String userId);
}
