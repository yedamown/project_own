package co.prjt.own.chall.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;

import co.prjt.own.common.Paging;

public interface CAmountService {
	//결제 충전 및 환급 시 등록
	int insertAmount(CAmountVO vo, CMemberVO mem);
	
	//결제 후 단건조회
	CAmountVO getAmt(CAmountVO vo);
	
	//전체 리스트 출력
	List<CAmountVO> getAmountListAll();
	
	//멤버로 출력
	List<CAmountVO> getAmountList(CAmountVO vo);
	
	//은행계좌출금 서비스 -> 컨트롤러에서 아작스로할때..하는거라고한다..
	ResponseEntity<JsonNode> refundMoney(CAmountVO vo); 
	
	/*-----------계좌 리스트 페이징------------------*/
	
	List<CAmountVO> getAmtListPage(CAmountVO vo, Paging paging);
	
}
