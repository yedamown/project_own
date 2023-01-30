package co.prjt.own.band.service;

import java.util.List;

import co.prjt.own.common.Paging;
import co.prjt.own.common.service.OwnLikeVO;


public interface BandBoardDetailService {
	//밴드의 총 글 개수조회
	public int countBandBoard(String bandNo);
	//더보기용 페이징..5개씩
	public List<BandBoardDetailSearchVO> getFiveBoard(BandBoardDetailSearchVO vo, String userId);
	//내가 맘찍은거 가져오기+ 글 다섯개
	public List<OwnLikeVO> getOwnLike(List<String> categoryNos, String userId);
	//페이징 모든 글 보기
	public List<BandBoardDetailSearchVO> getBandBoard(BandBoardDetailSearchVO vo, Paging paging);
	//페이징 모든 글 개수
	public int getBandBoardCount(BandBoardDetailSearchVO vo);
	//보드 상세 조회
	public BandBoardDetailSearchVO getBandBoardDetail(BandBoardDetailSearchVO vo);
	//보드 좋아요 상세 조회
	public List<OwnLikeVO> getOwnDetailLike(String bandBoardDetailNo);
	//글등록
	public BandBoardDetailSearchVO insertBandBoard(BandBoardDetailVO vo);
	//글업데이트
	public BandBoardDetailSearchVO updateBandBoard(BandBoardDetailVO vo);
	//일정업데이트
	public BandCalendarVO insertCalendar(BandCalendarVO vo);
	//일정가져오기(단건)
	public BandCalendarVO selectCalendar(String bandBoardDetailNo);
	//일정가져오기(다중)(밴드홈에서사용)
	public List<BandCalendarVO> selectCalendars(List<String> bandBoardDetailNo);
	//일정참여확인상세조회
	public List<BandCalendarDetailVO> selectCalendarDetail(String bandCalendarNo);
	//일정참여업데이트
	public List<BandCalendarDetailVO> updateCalendarDetail(BandCalendarDetailVO vo);
	//밴드그룹일정범위내 일정가져오기
	public List<BandCalendarVO> selectCalendarNum(String bandNo, String day);
	//밴드의 지정 달 일정 가져오기
	public List<BandCalendarVO> selectCalendarNow(String bandNo, String month);
	//밴드의 일정 새 글 쓰기
	public BandBoardDetailSearchVO bandCalendarInsert(String bandCalendarNo, BandBoardDetailVO vo);
	//게시글없이 캘린더만 단건입력
	public BandCalendarVO insertCalendarSingle(BandCalendarVO vo);
	//일정삭제
	public String deleteCalendar(String bandCalendarNo);
	//게시판설정업데이트
	public int updateBandBoardOption(BandBoardOptionVO vo);
	//글삭제 글번호 혹은 게시판번호사용(다중)
	public int deleteBandBoard(String bandBoardOptionNo);
}
