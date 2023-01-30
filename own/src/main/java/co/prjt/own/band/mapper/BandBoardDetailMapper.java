package co.prjt.own.band.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailVO;
import co.prjt.own.band.service.BandBoardOptionVO;
import co.prjt.own.band.service.BandCalendarDetailVO;
import co.prjt.own.band.service.BandCalendarVO;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.service.OwnLikeVO;

public interface BandBoardDetailMapper {
	//밴드의 총 글 개수조회
	public int countBandBoard(String bandNo);
	//더보기용 페이징..5개씩
	public List<BandBoardDetailSearchVO> getFiveBoard(BandBoardDetailSearchVO vo);
	//내가 맘찍은거 가져오기+ 글 다섯개
	public List<OwnLikeVO> getOwnLike(List<String> categoryNos, String userId);
	//페이징 게시판 글 보기
	public List<BandBoardDetailSearchVO> getBandBoard(BandBoardDetailSearchVO vo);
	//페이징 게시판 글 개수
	public int getBandBoardCount(BandBoardDetailSearchVO vo);
	//보드 상세 조회
	public BandBoardDetailSearchVO getBandBoardDetail(BandBoardDetailSearchVO vo);
	//보드 좋아요 상세 조회
	public List<OwnLikeVO> getOwnDetailLike(String bandBoardDetailNo);
	//글등록
	public int insertBandBoard(BandBoardDetailVO vo);
	//글업데이트
	public int updateBandBoard(BandBoardDetailVO vo);
	//일정업데이트
	public int insertCalendar(BandCalendarVO vo);
	//일정가져오기(단건) 캘린더번호로도 조회가능
	public BandCalendarVO selectCalendar(String bandBoardDetailNo);
	//일정가져오기(다중)(밴드홈에서사용)
	public List<BandCalendarVO> selectCalendars(List<String> bandBoardDetailNo);
	//일정참여확인상세조회
	public List<BandCalendarDetailVO> selectCalendarDetail(String bandCalendarNo);
	//일정참여삭제
	public int deleteCalendarDetail(BandCalendarDetailVO vo);
	//일정참여인서트업데이트
	public HashMap<String, Object> inupProCalendarDetail(HashMap<String, Object> inMap);
	//밴드그룹일정범위내 일정가져오기
	public List<BandCalendarVO> selectCalendarNum(String bandNo, String day);
	//밴드의 지정 달 일정 가져오기
	public List<BandCalendarVO> selectCalendarNow(String bandNo, String month);
	//밴드의 일정 새 글 쓰기
	public int bandCalendarInsert(BandBoardDetailVO vo);
	//캘린더번호와 글번호를 가지고 가서 업데이트
	public int updateBandCalendarBoardNo(String bandCalendarNo, String bandBoardDetailNo);
	//일정삭제
	public int deleteCalendar(String bandCalendarNo);
	//게시판설정업데이트
	public int updateBandBoardOption(BandBoardOptionVO vo);
	//글삭제 글번호 혹은 게시판번호사용(다중)//옵션창용....no를 다른거 씀 주의
	public int deleteBandBoard(String bandBoardOptionNo);
	//글번호 리스트로가져와서 삭제
	public int BandBoardDeleteList(BandBoardDetailSearchVO vo);
}
