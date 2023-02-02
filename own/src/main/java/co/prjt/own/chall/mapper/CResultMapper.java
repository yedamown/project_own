package co.prjt.own.chall.mapper;

import co.prjt.own.chall.service.CResultVO;

public interface CResultMapper {
	//신청과 동시에 도전번호, 아이디, 도전횟수 입력
	int insertCResult(CResultVO vo);

	//마무리 후, 정산하기 - 끝 회원도전횟수, 성공률, 환급률 계산 
	int updateResult(CResultVO vo);
	
	//상금계산해서 업뎃
	int updateReward(CResultVO vo);
	
	//단건조회
	CResultVO getResult(CResultVO vo);
		
	//달성률 평균 구하기
	double avgSuccessRate(CResultVO vo);
	
}
