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
import co.prjt.own.band.service.BandBoardDetailVO;
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
		// 이미지조회(썸머노트로 인해 인라인 형식의 src첨부가 되면서 주석처리)
//		List<MultimediaVO> imgs = common.selectImgAll(vo.getBandBoardDetailNo());
//		if(imgs!=null) {
//			board.setBandImgs(common.selectImgAll(vo.getBandBoardDetailNo()));
//		}
		return board;
	}

	@Override
	public List<OwnLikeVO> getOwnDetailLike(String bandBoardDetailNo) {
		return bandBoardDetailMapper.getOwnDetailLike(bandBoardDetailNo);
	}

	@Override
	public BandBoardDetailSearchVO insertBandBoard(BandBoardDetailVO vo) {
		//bandBoardDetailNo 여기에 이미지정보들어있음..담아놓고
//		String img = vo.getBandBoardDetailNo();
		
		int r = bandBoardDetailMapper.insertBandBoard(vo);
		//성공하면 글조회
		BandBoardDetailSearchVO searchVo = null;
		if(r>0) {
			searchVo = BandBoardDetailSearchVO.builder()
					.bandBoardDetailNo(vo.getBandBoardDetailNo())
					.build();
			//글생성이 성공했으면 searchVo에 널 값 아닌 게 담길 것(숫자임)
			searchVo.setBandBoardDetailNo("BDD_"+searchVo.getBandBoardDetailNo());
			bandBoardDetailMapper.getBandBoardDetail(searchVo);
			
			//p태그(컨텐츠)에서 이미지를 추출하겠음
			String[] pImgs = vo.getBandBoardContent().split("<img src=\"/imgView/");
			System.out.println("0000"+pImgs[0]);
			List<String> pNewImgs = new ArrayList<String>();
			//pimgs[0]은 공백임
			if(pImgs.length>0) {
				for(int i=1; i<pImgs.length; i++) {
					//자르기..
					pImgs[i] = pImgs[i].substring(0, pImgs[i].indexOf("\" style=\""));
					pNewImgs.add(pImgs[i]);
					System.out.println(pImgs[i]);
				}
				//newImgs가지고 이미지 경로 수정하기
				int re = common.updateKey("BDD_"+vo.getBandBoardDetailNo(), pNewImgs);
				System.out.println("사진"+re+"건 키 값 수정완료됨");
			}
		}
		return searchVo;
	}

	@Override
	public BandBoardDetailSearchVO updateBandBoard(BandBoardDetailVO vo) {
		int r = bandBoardDetailMapper.updateBandBoard(vo);
		//리턴할 보 만들기
		BandBoardDetailSearchVO returnVo = new BandBoardDetailSearchVO();
		//업데이트 후 이미지 수정하고 서치 한 후 보내기
		System.out.println(r+"건 수정");
		if(r>0) {
			//글 수정이 성공했으면 searchVo에 널 값 아닌 게 담길 것..상세조회해주기
			returnVo.setBandBoardDetailNo(vo.getBandBoardDetailNo());
			returnVo = bandBoardDetailMapper.getBandBoardDetail(returnVo);
			
			//p태그(컨텐츠)에서 이미지를 추출하겠음
			String[] pImgs = vo.getBandBoardContent().split("<img src=\"/imgView/");
			List<String> pNewImgs = new ArrayList<String>();
			//pimgs[0]은 공백임
			if(pImgs.length>1) {
				for(int i=1; i<pImgs.length; i++) {
					//자르기..
					pImgs[i] = pImgs[i].substring(0, pImgs[i].indexOf("\" style=\""));
					pNewImgs.add(pImgs[i]);
				}
				//newImgs가지고 이미지 경로 수정하기
				int re = common.updateKey("BDD_"+vo.getBandBoardDetailNo(), pNewImgs);
				System.out.println("사진"+re+"건 키 값 수정완료됨");
				
				//수정 중 삭제된 이미지가 있다면 db에서 삭제해주기 pNewImgs랑 같지않은 것들 삭제
				int reD = common.deleteImg(pNewImgs);
				System.out.println(reD+"건 수정하면서 이미지 삭제됨");
			}
		}
		return returnVo;
	}
}
