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
}
