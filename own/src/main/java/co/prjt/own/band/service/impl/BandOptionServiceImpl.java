package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandOptionMapper;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandOptionService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;

@Service
public class BandOptionServiceImpl implements BandOptionService {
	@Autowired
	BandOptionMapper bandOptionMapper;
	
	@Override
	public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO bmdvo, BandVO vo, Paging paging) {
		// 페이징 설정
		paging.setTotalRecord(bandOptionMapper.bandCount(bmdvo));
		// 가치 설정 - 멤버 관리 - 전체 멤버 리스트
		return bandOptionMapper.bandOptionGetAllMemberList(bmdvo);
		
	}	
	
	@Override
	public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandManageHome(vo);
	}

	@Override
	public int bandCount(BandMemberDetailVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandCount(vo);
	}

	@Override
	public BandVO bandInfo(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandInfo(vo);
	}

	@Override
	public int bandUpdate(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandUpdate(vo);
	}

	@Override
	public int bandPass(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandPass(vo);
	}

	@Override
	public int bandHuman(String bandNo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandHuman(bandNo);
	}

	@Override
	public int bandDisHuman(String bandNo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandDisHuman(bandNo);
	}

}
