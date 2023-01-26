package co.prjt.own.band.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardDetailVO;
import co.prjt.own.band.service.BandCalendarDetailVO;
import co.prjt.own.band.service.BandCalendarVO;
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
		if(fiveboard.size()>0) {
			for(BandBoardDetailSearchVO board : fiveboard) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(board.getBandBoardDetailNo())){
						board.setBandImg(img);
						break;
					}
				}
			}
		}
		//일정넣어주기
		List<BandCalendarVO> calList = bandBoardDetailMapper.selectCalendars(bandNoList);
		if(calList.size()>0) {
			for(BandBoardDetailSearchVO board : fiveboard) {
				for(BandCalendarVO cal : calList) {
					if(cal.getBandBoardDetailNo().equals(board.getBandBoardDetailNo())){
						board.setBandCalendar(cal);
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
		//일정있는지 검색해서 넣기(impl)
		board.setBandCalendar(bandBoardDetailMapper.selectCalendar(vo.getBandBoardDetailNo()));
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
			
			// p태그(컨텐츠)에서 이미지를 추출하겠음
			List<String> pNewImgs = new ArrayList<String>();
			//src 뽑는 정규식
			Pattern p = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	        Matcher m = p.matcher(vo.getBandBoardContent());
	        //찾아서 첫번째꺼? 리스트에넣음
	        while (m.find()) { 
	            pNewImgs.add(m.group(1).substring(9));
	        }
			if(pNewImgs.size()>0) {
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
			

			// p태그(컨텐츠)에서 이미지를 추출하겠음
			List<String> pNewImgs = new ArrayList<String>();
			//src 뽑는 정규식
			Pattern p = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	        Matcher m = p.matcher(vo.getBandBoardContent());
	        //찾아서 첫번째꺼? 리스트에넣음
	        while (m.find()) { 
	            pNewImgs.add(m.group(1).substring(9));
	        }
	        if(pNewImgs.size()>0) {
				//newImgs가지고 이미지 경로 수정하기
				int re = common.updateKey(returnVo.getBandBoardDetailNo(), pNewImgs);
				System.out.println("사진"+re+"건 키 값 수정완료됨");
				
				//수정 중 삭제된 이미지가 있다면 db에서 삭제해주기 pNewImgs랑 같지않은 것들 삭제
				int reD = common.deleteImg(pNewImgs, returnVo.getBandBoardDetailNo());
				System.out.println(reD+"건 수정하면서 이미지 삭제됨");
			}
		}
		return returnVo;
	}

	@Override
	public BandCalendarVO insertCalendar(BandCalendarVO vo) {
		int r = bandBoardDetailMapper.insertCalendar(vo);
		System.out.println(r+"건 캘린더");
		//캘린더 인서트 후 서치해서 보내기
		bandBoardDetailMapper.selectCalendar(vo.getBandBoardDetailNo());
		return bandBoardDetailMapper.selectCalendar(vo.getBandBoardDetailNo());
	}

	@Override
	public BandCalendarVO selectCalendar(String bandBoardDetailNo) {
		return bandBoardDetailMapper.selectCalendar(bandBoardDetailNo);
	}

	@Override
	public List<BandCalendarVO> selectCalendars(List<String> bandBoardDetailNo) {
		return bandBoardDetailMapper.selectCalendars(bandBoardDetailNo);
	}

	@Override
	public List<BandCalendarDetailVO> selectCalendarDetail(String bandCalendarNo) {
		return bandBoardDetailMapper.selectCalendarDetail(bandCalendarNo);
	}

	@Override
	public List<BandCalendarDetailVO> updateCalendarDetail(BandCalendarDetailVO vo) {
		//1.없음=>누르면 인서트
		//2.있는데=>누르면 업데이트
		//3.있는거없애면=>딜리트
		int r = 0;
		if(vo.getBandAttend()==null||vo.getBandAttend().equals("")) {
			//VO를 받았는데 값이 비어있음..즉 삭제(3)
			r = bandBoardDetailMapper.deleteCalendarDetail(vo);
			System.out.println(r+"건 일정투표삭제");
		} else {
			//값이 있음..업데이트 혹은 인서트 프로시져 999면 업데이트 //888이면 인서트
			HashMap<String, Object> inMap = new HashMap<>();
			inMap.put("bandCalendarNo", vo.getBandCalendarNo());
			inMap.put("bandMemberNo", vo.getBandMemberNo());
			inMap.put("bandAttend", vo.getBandAttend());
			inMap.put("pNum", -1);
			bandBoardDetailMapper.inupProCalendarDetail(inMap);
				System.out.println(inMap.get("pNum")+"999:업데이트 888:인서트 일정inup");
		}
		List<BandCalendarDetailVO> list = bandBoardDetailMapper.selectCalendarDetail(vo.getBandCalendarNo());
		return list;
	}

	@Override
	public List<BandCalendarVO> selectCalendarNum(String bandNo, String day) {
		return bandBoardDetailMapper.selectCalendarNum(bandNo, day);
	}

	@Override
	public List<BandCalendarVO> selectCalendarNow(String bandNo, String month) {
		return bandBoardDetailMapper.selectCalendarNow(bandNo, month);
	}

	@Override
	public BandBoardDetailSearchVO bandCalendarInsert(String bandCalendarNo, BandBoardDetailVO vo) {
		int r = bandBoardDetailMapper.insertBandBoard(vo);
		System.out.println(vo.toString()+"???업데이트 후");
		if(r>0) {
			//캘린더번호와 글번호를 가지고 가서 업데이트
			r = bandBoardDetailMapper.updateBandCalendarBoardNo(bandCalendarNo, "BDD_"+vo.getBandBoardDetailNo());
		}
		//상세조회해서 리턴
		BandBoardDetailSearchVO voD = new BandBoardDetailSearchVO();
		if(r>0) {
			voD.setBandBoardDetailNo("BDD_"+vo.getBandBoardDetailNo());
			bandBoardDetailMapper.getBandBoardDetail(voD);
		}
		return voD;
	}

	@Override
	public BandCalendarVO insertCalendarSingle(BandCalendarVO vo) {
		int r = bandBoardDetailMapper.insertCalendar(vo);
		System.out.println(r+"캘린더입력됨");
		return vo;
	}
}
