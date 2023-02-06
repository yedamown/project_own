package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.common.Paging;

public interface CAmountMapper {
	//결제 충전 및 환급 시 등록
	int insertAmount(CAmountVO vo);
		
	//결제 후 단건조회
	CAmountVO getAmt(CAmountVO vo);
		
	//전체 리스트 출력
	List<CAmountVO> getAmountListAll();
		
	//vo로 검색하기 + 페이징정보
	List<CAmountVO> getAmountList(CAmountVO vo);
	
	/*-----------계좌 리스트 페이징------------------*/
		
	int countMyAMT(CAmountVO vo);
	//검색은 위에 getAmountList(CAmountVO vo)로 검색하기
}
