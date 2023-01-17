package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandBoardDetailVO;

public interface BandBoardDetailMapper {
	//밴드의 총 글 개수조회
	public int countBandBoard(String bandNo);
	//더보기용 페이징..5개씩
	public List<BandBoardDetailVO> getFiveBoard(BandBoardDetailVO vo);
}
