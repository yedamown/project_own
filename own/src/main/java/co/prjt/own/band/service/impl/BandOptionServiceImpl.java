package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandOptionMapper;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandOptionService;
import co.prjt.own.band.service.BandVO;

@Service
public class BandOptionServiceImpl implements BandOptionService {
	@Autowired
	BandOptionMapper bandOptionMapper;
	
	@Override
	public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo) {
		// 가치 설정 - 멤버 관리 - 전체 멤버 리스트
		return bandOptionMapper.bandOptionGetAllMemberList(vo);
	}	
	
	@Override
	public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandManageHome(vo);
	}

	@Override
	public int bandCount(BandVO vo) {
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

}
