package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMemberDetailMapper;
import co.prjt.own.band.mapper.BandOptionMapper;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.service.MessageVO;
import co.prjt.own.common.mapper.CommonMapper;

@Service
public class BandMemberDetailServiceImpl implements BandMemberDetailService{
	@Autowired BandMemberDetailMapper bandMemberDetailMapper;
	@Autowired CommonMapper commonMapper;
	@Autowired
	BandOptionMapper bandOptionMapper;
	
	@Override
	public List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo) {
		// 밴드 회원 목록 띄우기 + 채팅방 생성여부
		vo.setBandMemberStatus("BA02"); // 조건 설정
		vo.getPaging().setTotalRecord(bandOptionMapper.bandCount(vo));
		vo.getPaging().setPageSize(5);
		vo.setFirst(vo.getPaging().getFirst());
		vo.setLast(vo.getPaging().getLast());
		vo.setPaging(vo.getPaging());
		
		List<BandMemberDetailVO> list = bandMemberDetailMapper.bandMemberList(vo);
		if(list!=null && list.size()>0) {
			list.get(0).setPaging(vo.getPaging());
		}
		return list;
	}

	@Override
	public String getBandMemberNo(BandMemberDetailVO vo) {
		// 밴드번호 + 유저아이디로 밴드멤버번호 가져오기.
		return bandMemberDetailMapper.getBandMemberNo(vo);
	}

	@Override
	public String getBandMemberNickname(MessageVO vo) {
		// 밴드멤버식별번호로 닉네임 가져오기.
		return bandMemberDetailMapper.getBandMemberNickname(vo);
	}

	@Override
	public BandMemberDetailVO getBandMemberDetail(BandMemberDetailVO vo) {
		vo = bandMemberDetailMapper.getBandMemberDetail(vo);
		//검색된 정보가 있으면
		if(vo!=null) {
			vo.setDetailImg(commonMapper.selectImg(vo.getBandMemberNo()));
		}
		return vo;
	}

	@Override
	public BandMemberDetailVO insertBandMemberDetail(BandMemberDetailVO vo) {
		//if(getBandMemberStatus에 membershipoption을 담아둠)의 값에 따라 멤버의 현재상태 ..(승인중? / 바로 가입됨)
		//만약 승인없이 회원가입이 안된다면 승인대기회원으로....아니면 바로 승인
		if(vo.getBandMemberStatus().equals("BF01")) {
			vo.setBandMemberStatus("BA01");
		} else {
			vo.setBandMemberStatus("BA02");
		}
		int r = bandMemberDetailMapper.insertBandMemberDetail(vo);
		System.out.println(r+"건 입력 됨");
		return vo;
	}

	@Override
	public int bandProfilImg(String value) {
		String mediaNo = null;
		return bandMemberDetailMapper.bandProfilImg(mediaNo, value);
	}

	@Override 
	public int bandProfilDefImg(String defaultNo, String detailNo, String mediaServerFile) {
		return bandMemberDetailMapper.bandProfilDefImg(defaultNo, detailNo, mediaServerFile);
	}

	@Override
	public int duplicateChk(String bandNo, String nickName) {
		return bandMemberDetailMapper.duplicateChk(bandNo, nickName);
	}

	@Override
	public int myOptionUpdate(BandMemberDetailVO vo) {
		return bandMemberDetailMapper.myOptionUpdate(vo);
	}

	@Override
	public List<BandMemberDetailVO> getBandMemberNickProfile(BandMemberDetailVO vo) {
		return bandMemberDetailMapper.getBandMemberNickProfile(vo);
	}
}
