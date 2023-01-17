package co.prjt.own.chall.mapper;

import co.prjt.own.chall.service.CResultVO;

public interface CResultMapper {
	//식별번호, 아이디, 도전번호, 도전인증횟수 등록
	int insertCResult(CResultVO vo);
	
	//회원 인증횟수, 성공률 업뎃, 환급금 업뎃
	int updateCResult(CResultVO vo);
	
	//상금계산해서 업뎃
	int updateReward(CResultVO vo);
}
