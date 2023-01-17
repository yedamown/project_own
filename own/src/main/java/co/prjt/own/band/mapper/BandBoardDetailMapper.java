package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.common.service.OwnLikeVO;

public interface BandBoardDetailMapper {
	//밴드의 총 글 개수조회
	public int countBandBoard(String bandNo);
	//더보기용 페이징..5개씩
	public List<BandBoardDetailSearchVO> getFiveBoard(BandBoardDetailSearchVO vo);
	//내가 맘찍은거 가져오기+ 글 다섯개
	public List<OwnLikeVO> getOwnLike(List<String> categoryNos, String userId);
}
