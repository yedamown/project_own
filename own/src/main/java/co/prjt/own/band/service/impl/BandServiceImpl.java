package co.prjt.own.band.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.mapper.BandMemberDefaultMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;

@Service
public class BandServiceImpl implements BandService{
	
	@Autowired BandMapper bandMapper;
	@Autowired BandMemberDefaultMapper bandMemberDefaultMapper;
	@Autowired CommonService common;
	@Override
	public BandVO insertBand(BandVO vo) {
		bandMapper.insertBand(vo);
		return vo;
	}
	
	@Override
	public int bandSampleimg(MultimediaVO vo) {
		return bandMapper.bandSampleimg(vo);
	}
	
	@Override
	public int updateBand(BandVO vo) {
		return bandMapper.updateBand(vo);
	}

	@Override
	public List<Map<String, Object>> getBandAll(BandVO vo, Paging paging) {
		paging.setTotalRecord(bandMapper.count(vo));
		paging.setPageUnit(8);
		//임시 5..
		paging.setPageSize(5);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		//밴드 이미지 담기
		List<Map<String, Object>> bandList = bandMapper.getBandAll(vo);
		//bandList에 이미지 붙이기용
		List<String> bandNoList = new ArrayList<String>();
		for(Map<String, Object> band : bandList) {
			bandNoList.add((String) band.get("bandNo"));
		}
		if(bandList.size()>0) {
			//위에서 가져온 bandNoList리스트로 이미지 얻어오기
			List<MultimediaVO> imglist = common.selectImgAllKey(bandNoList);
			for(Map<String, Object> band : bandList) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(band.get("bandNo"))){
						band.put("bandMainImg", img);
					}
				}
			}
		}
return bandList;
	}

	@Override
	public int count(BandVO vo) {
		return bandMapper.count(vo);
	}

	@Override
	public Map<String, Object> getBand(String bandNo) {
		Map<String, Object> vo = bandMapper.getBand(bandNo);
		if(vo.get("bandAgeAfteroption")==null) {
			vo.put("bandAgeAfteroption", "");
		}
		if(vo.get("bandAgeBeforoption")==null) {
			vo.put("bandAgeBeforoption", "");
		}
		if(vo.get("bandGender")==null) {
			vo.put("bandGender", "");
		}
		//이미지넣기
		MultimediaVO mul = common.selectImg(bandNo);
		vo.put("bandImg", mul);
		return vo;
	}

	@Override
	public List<BandVO> getMemberBandAll(BandMemberDetailVO vo) {
		return bandMapper.getMemberBandAll(vo);
	}

	@Override
	public List<Map<String, Object>> getBandRecentAll(BandMemberDetailVO vo) {
		//이미지도 넣기
		List<Map<String, Object>> bandList = bandMapper.getBandRecentAll(vo);
		//출력용으로 3개만남기기
		if(bandList.size()>4) {
			bandList = bandList.subList(0, 3);
		}
		//bandList에 이미지 붙이기용
		List<String> bandNoList = new ArrayList<String>();
		for(Map<String, Object> band : bandList) {
			bandNoList.add((String) band.get("bandNo"));
		}
		List<MultimediaVO> imglist = new ArrayList<MultimediaVO>();
		//위에서 가져온 bandNoList리스트로 이미지 얻어오기
		if(bandNoList.size()>0) {
			imglist = common.selectImgAllKey(bandNoList);
		}
		if(bandList.size()>0) {
			for(Map<String, Object> band : bandList) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(band.get("bandNo"))){
						band.put("bandMainImg", img);
					}
				}
			}
		}
		return bandList;
	}

	@Override
	public List<Map<String, Object>> getMyBandAll(BandVO band, Paging paging) {
		//페이지 3개로 임시..
		paging.setPageUnit(3);
		//임시 5..
		paging.setPageSize(5);
		//페이징용 개인의 가입밴드 수
		BandMemberDetailVO detail = new BandMemberDetailVO();
		detail.setUserId(band.getBandLeaderid());
		//전체 페이지계산
		paging.setTotalRecord(bandMapper.count2(detail.getUserId(), band.getBandName()));
		band.setFirst(paging.getFirst());
		band.setLast(paging.getLast());
		//
		//이미지도 넣기
		List<Map<String, Object>> bandList = bandMapper.getMyBandAll(band);
		//출력용으로 3개만남기기
		if(bandList.size()>4) {
			bandList = bandList.subList(0, 3);
		}
		//bandList에 이미지 붙이기용
		List<String> bandNoList = new ArrayList<String>();
		for(Map<String, Object> b : bandList) {
			bandNoList.add((String) b.get("bandNo"));
		}
		//위에서 가져온 bandNoList리스트로 이미지 얻어오기
		List<MultimediaVO> imglist = new ArrayList<MultimediaVO>();
		if(bandList.size()>0) {
			imglist = common.selectImgAllKey(bandNoList);
			for(Map<String, Object> b : bandList) {
				System.out.println(b.toString());
				for(MultimediaVO img : imglist) {
					System.out.println(img.toString());
					if(img.getIdentifyId().equals(b.get("bandNo"))){
						b.put("bandMainImg", img);
					}
				}
			}
		}
		return bandList;
	}

	@Override
	public int count2(BandMemberDetailVO vo, BandVO vo2) {
		return bandMapper.count2(vo.getUserId(), vo2.getBandName());
	}

	@Override
	public List<Map<String, Object>> threeBand(String threeBand) {
		//한문장으로 된 번호를 보냄..ex)BDU_17BDU_15BDU_14 
		String bandimsi = "";
		List<String> bandNos = new ArrayList<String>();
		//잘라서 배열로만들기
		while(threeBand.lastIndexOf("BDU")>=0) {
			bandimsi = threeBand.substring(threeBand.lastIndexOf("BDU"));
			threeBand = threeBand.substring(0, threeBand.lastIndexOf("BDU"));
			bandNos.add(bandimsi);
		}
		//BandVO불러서 세팅
		BandVO band = new BandVO();
		band.setBandNos(bandNos);
		
		return bandMapper.threeBand(band);
	}

	@Override
	public List<Map<String, String>> allLocation() {
		return bandMapper.allLocation();
	}

	@Override
	public List<Map<String, String>> allExcersie() {
		return bandMapper.allExcersie();
	}

	@Override //추천밴드 밴드메인표시용
	public List<BandVO> recomBand(String userId) {
		//매퍼를 돌릴 vo
		BandVO band = new BandVO();
		
		String[] arr = userId.split("-");
		System.out.println("변수명"+userId+"도달함");
		System.out.println(arr[0]);//아이디
		//유저디폴트에서 정보값가져와야 함(기본적으로 깔아놓음)
		System.out.println(arr[0]);
		//설정값으로 바꿔줌
		if(arr[1].equals("all")) {//지역설정값all이 들어옴
			band.setBandLocation(null);
		} else {
			band.setBandLocation(arr[1]);//지역설정값
		}
		if(arr[2].equals("all")) {//운동설정값있으면...
			band.setBandCategory(null);
		} else {
			band.setBandCategory(arr[2]);//운동설정값
		}
		//밴드멤버의 설정을 밴드에 담아서 추천을가져옴..
		//가입된 밴드는 제외하기위에 유저명담음
		band.setBandLeaderid(arr[0]);
		//생일하고 성별설정가져오기
		BandMemberDefaultVO defVo = bandMemberDefaultMapper.getBandMemberDefault(arr[0]);
		band.setBandAgeBeforoption(defVo.getBandBirth());
		band.setBandGender(defVo.getBandGender());
		//설정으로 추천리스트받아옴
		List<BandVO> list = bandMapper.recomBand(band);
		//4개만 남기고 자르기
		if(list.size()>5) {
			list = list.subList(0, 4);
		}
		//리스트에 이미지담기
		List<String> bandNoList = new ArrayList<String>();
		for(BandVO b : list) {
			bandNoList.add(b.getBandNo());
		}
		//위에서 가져온 list리스트로 이미지 얻어오기
		List<MultimediaVO> imglist = null;
		if(list.size()>0) {
			imglist = common.selectImgAllKey(bandNoList);
			for(BandVO b : list) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(b.getBandNo())){
						b.setBandMainImg(img);
					}
				}
			}
		}
		return list;
	}

	@Override//추천밴드 페이지이동용
	public List<BandVO> recomBandPage(BandVO band, Paging paging) {
		//매퍼를 돌릴 vo
		//설정으로 추천리스트받아옴
		paging.setPageUnit(8);
		//임시 5..
		paging.setPageSize(5);
		//전체 페이지계산
		paging.setTotalRecord(bandMapper.recomBandCount(band));
		band.setFirst(paging.getFirst());
		band.setLast(paging.getLast());
		//페이지카운트..용을 해야하는데 힘드니 list size로 대체
		List<BandVO> list = bandMapper.recomBand(band);
		//리스트에 이미지담기
		List<String> bandNoList = new ArrayList<String>();
		for(BandVO b : list) {
			bandNoList.add(b.getBandNo());
		}
		//위에서 가져온 list리스트로 이미지 얻어오기
		List<MultimediaVO> imglist = null;
		if(list.size()>=1) {
			imglist = common.selectImgAllKey(bandNoList);
			for(BandVO b : list) {
				for(MultimediaVO img : imglist) {
					if(img.getIdentifyId().equals(b.getBandNo())){
						b.setBandMainImg(img);
					}
				}
			}
		}
		return list;
	}
	//밴드 포토찾기
	public List<BandBoardDetailSearchVO> bandPhotos(String bandNo, String mediaNo){
		return bandMapper.bandPhotos(bandNo, mediaNo);
	}

	@Override
	public BandVO getBandByBoardOptionNo(String value) {
		return bandMapper.getBandByBoardOptionNo(value);
	};
}
