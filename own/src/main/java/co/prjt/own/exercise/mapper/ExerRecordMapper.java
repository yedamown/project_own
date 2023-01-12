package co.prjt.own.exercise.mapper;

import java.util.List;

import co.prjt.own.exercise.service.ExerRecordVO;

public interface ExerRecordMapper {
	// 오운완(나의운동기록) 등록
	int insertExerRecord(ExerRecordVO vo);

	// 오운완(나의운동기록) 수정
	int updateExerRecord(ExerRecordVO vo);

	// 오운완(나의운동기록) 조회
	List<ExerRecordVO> ExerRecordList(String userId);

	// 오운 유저의 운동 기록 중 가장 최신 데이터들 조회
	List<ExerRecordVO> LatestExerRecord(String userId);

	// 최신 데이터에서 개수 카운팅
	List<ExerRecordVO> DayRecordCounting(String userId);
}
