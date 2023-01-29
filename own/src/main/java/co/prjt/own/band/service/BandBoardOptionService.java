package co.prjt.own.band.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface BandBoardOptionService {
	//게시판검색 동시에 글 개수도
	public List<BandBoardOptionVO> getBandBoardList(String bandNo);
	//게시판설정업데이트
	public int updateBandBoardOption(BandBoardOptionVO vo);
	//게시판 삭제
	public int deleteBandBoardOption(String bandBoardOptionNo);
	//게시판 생성 insertBandBoardOptionLine
	public int insertBandBoardOption(String bandNo);
	//게시판 줄 생성 insertBandBoardOptionLine
	public int insertBandBoardOptionLine(String bandNo);
	//게시판 순서 변경
	public int bandOption9LineUpdate(List<Map<String, String>> obj);
}
