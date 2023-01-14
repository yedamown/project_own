package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.CAmountVO;

public interface CAmountMapper {
	// 결제 충전 시 등록
	int insertAmount(CAmountVO vo);

	// 전체 리스트 출력
	List<CAmountVO> getAmountListAll();

	// 멤버로 출력
	List<CAmountVO> getAmountList(CAmountVO vo);
}
