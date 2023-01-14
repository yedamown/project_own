package co.prjt.own.band.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMemberDefaultMapper;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;

@Service
public class BandMemberDefaultServiceImpl implements BandMemberDefaultService{
	
	@Autowired BandMemberDefaultMapper bandMemberDefaultMapper;

	@Override
	public BandMemberDefaultVO getBandMemberDefault(String userId) {
		return bandMemberDefaultMapper.getBandMemberDefault(userId);
	}

	@Override
	public int updateMemberDf(BandMemberDefaultVO vo) {
		return bandMemberDefaultMapper.updateMemberDf(vo);
	}
	
}
