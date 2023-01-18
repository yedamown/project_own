package co.prjt.own.exercise.mapper;

import java.util.List;

import co.prjt.own.exercise.service.ExerRecordVO;

public interface ExerRecordMapper {
	// 오운완(나의운동기록) 등록
	int insertExerRecord(ExerRecordVO vo);

	// 오운 유저의 운동 기록 중 가장 최신 데이터들 조회
	List<ExerRecordVO> LatestExerRecord(String userId);

	// 최신 데이터에서 개수 카운팅
	List<ExerRecordVO> dayRecordCounting(String userId);

	// 최신 데이터 날짜~7일 간의 날짜와 몸무게 출력
	List<ExerRecordVO> getWeight(String userId);

	// 기간 설정해서 운동 데이터 조회
	List<ExerRecordVO> selectRecord(ExerRecordVO vo);

	// 기간 설정해서 몸무게 조회
	List<ExerRecordVO> selectWeight(ExerRecordVO vo);

	// 기간 설정해서 운동 카운팅
	List<ExerRecordVO> selectCounting(ExerRecordVO vo);
}
