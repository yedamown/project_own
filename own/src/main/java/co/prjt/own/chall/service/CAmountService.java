package co.prjt.own.chall.service;

import java.util.List;

public interface CAmountService {
	//결제 충전 시 등록
	int insertAmount(CAmountVO vo);
	
	//전체 리스트 출력
	List<CAmountVO> getAmountListAll();
	
	//멤버로 출력
	List<CAmountVO> getAmountList(CAmountVO vo);
	
}
