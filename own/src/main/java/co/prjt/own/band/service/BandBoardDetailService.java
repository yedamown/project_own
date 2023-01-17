package co.prjt.own.band.service;

import java.util.List;

public interface BandBoardDetailService {
	//밴드의 총 글 개수조회
	public int countBandBoard(String bandNo);
	//더보기용 페이징..5개씩
	public List<BandBoardDetailVO> getFiveBoard(BandBoardDetailVO vo);
}
