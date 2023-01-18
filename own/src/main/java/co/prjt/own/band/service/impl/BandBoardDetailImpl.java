package co.prjt.own.band.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeVO;


@Service
public class BandBoardDetailImpl implements BandBoardDetailService{
	@Autowired BandBoardDetailMapper bandBoardDetailMapper;
	@Autowired CommonService common;
	
	@Override
	public int countBandBoard(String bandNo) {
		return bandBoardDetailMapper.countBandBoard(bandNo);
	}

	@Override
	public List<BandBoardDetailSearchVO> getFiveBoard(BandBoardDetailSearchVO vo, String userId) {
		//글 5개씩..+댓글 수 담기 (밴드식별+직전에 뿌린 마지막번호이용)..만약없다면..
		List<BandBoardDetailSearchVO> fiveboard = bandBoardDetailMapper.getFiveBoard(vo);
		
		List<String> categoryNos = new ArrayList<String>();
		for(BandBoardDetailSearchVO v : fiveboard) {
			categoryNos.add(v.getBandBoardDetailNo());
		}
		//찜 만약 내가 찍었다면 찍었다는 게 필요할 듯.. 단순 수량만 가져가면 안되겠음
		List<OwnLikeVO> list = bandBoardDetailMapper.getOwnLike(categoryNos, userId);
		//확장보드VO에 좋아요VO담담음 BoardVO.like.likecount로 꺼내쓰기
			for(BandBoardDetailSearchVO v : fiveboard) {
				for(OwnLikeVO o : list) {
					if(o.getCategoryNo()!=null&&v.getBandBoardDetailNo().equals(o.getCategoryNo())) {
						v.setLikeList(o);
					} else {
						OwnLikeVO like = new OwnLikeVO();
						like.setLikeCount(0);
						like.setUserId("");
						v.setLikeList(like);
					}
				}//널체크..제정신일 때
				if(list.size()==0) {
					OwnLikeVO like = new OwnLikeVO();
					like.setLikeCount(0);
					like.setUserId("");
					v.setLikeList(like);
				}
			}
		//사진넣어주자
		//bandList에 이미지 붙이기용
		List<String> bandNoList = new ArrayList<String>();
		for(BandBoardDetailSearchVO board : fiveboard) {
			bandNoList.add(board.getBandBoardDetailNo());
		}
		//위에서 가져온 bandNoList리스트로 이미지 얻어오기
		List<MultimediaVO> imglist = common.selectImgAllKey(bandNoList);
		if(fiveboard.size()>1) {
			for(BandBoardDetailSearchVO board : fiveboard) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(board.getBandBoardDetailNo())){
						board.setBandImg(img);
						break;
					}
				}
			}
		}
		return fiveboard;
	}

	@Override
	public List<OwnLikeVO> getOwnLike(@Param(value = "String") List<String> categoryNos, @Param(value = "userId") String userId) {
		return bandBoardDetailMapper.getOwnLike(categoryNos, userId);
	}

	@Override
	public List<BandBoardDetailSearchVO> getBandBoard(BandBoardDetailSearchVO vo, Paging paging) {
		paging.setTotalRecord(bandBoardDetailMapper.getBandBoardCount(vo));
		paging.setEndPage(15);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println(paging.toString());
		//밴드번호가 매퍼돌리고오면 누락돼서 넣어줌
		List<BandBoardDetailSearchVO> list = bandBoardDetailMapper.getBandBoard(vo);
		list.get(0).setBandNo(vo.getBandNo());
		//paging객체도 돌려보내야 할 듯...
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	public int getBandBoardCount(BandBoardDetailSearchVO vo) {
		return bandBoardDetailMapper.getBandBoardCount(vo);
	}

	@Override
	public BandBoardDetailSearchVO getBandBoardDetail(BandBoardDetailSearchVO vo) {
		// 글단건조회(글+유저별명)
		BandBoardDetailSearchVO board = bandBoardDetailMapper.getBandBoardDetail(vo);
		// 이미지조회
		board.setBandImgs(common.selectImgAll(vo.getBandBoardDetailNo()));
		return board;
	}

	@Override
	public List<OwnLikeVO> getOwnDetailLike(String bandBoardDetailNo) {
		return bandBoardDetailMapper.getOwnDetailLike(bandBoardDetailNo);
	}
}
