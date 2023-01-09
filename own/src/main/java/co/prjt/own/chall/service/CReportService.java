package co.prjt.own.chall.service;

import java.util.List;

public interface CReportService {
	//신고신청
	CReportVO insertCreport(CReportVO vo);
	
	//신고상태 + 신고처리하면서 사유입력 (수정)
	CReportVO updateCreport(CReportVO vo);
	
	// 내 신고내역 / 도전 신고내역 보기 / 
	//	도전리더 처리상태(도전번호, 상태로검색)
	List<CReportVO> getCReport(CReportVO vo);
	
	//전체신고내역
	List<CReportVO> getCReportAll(CReportVO vo);
}
