package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandBoardOptionVO;


public interface BandBoardOptionMapper {
	//게시판검색 동시에 글 개수도
	public List<BandBoardOptionVO> getBandBoardList(String bandNo);
	//게시판설정업데이트
	public int updateBandBoardOption(BandBoardOptionVO vo);
	//게시판 삭제
	public int deleteBandBoardOption(String bandBoardOptionNo);
	//게시판 생성
	public int insertBandBoardOption(BandBoardOptionVO vo);
	//게시판 줄 생성 insertBandBoardOptionLine
	public int insertBandBoardOptionLine(BandBoardOptionVO bandNo);
	//게시판 순서 변경
	public int bandOption9LineUpdate(BandBoardOptionVO vo);
}
