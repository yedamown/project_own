package co.prjt.own.band.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMemberDefaultMapper;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.common.mapper.CommonMapper;

@Service
public class BandMemberDefaultServiceImpl implements BandMemberDefaultService{
	
	@Autowired BandMemberDefaultMapper bandMemberDefaultMapper;
	@Autowired CommonMapper commonMapper;

	@Override
	public BandMemberDefaultVO getBandMemberDefault(String userId) {
		//프로필 이미지넣음
		BandMemberDefaultVO vo = bandMemberDefaultMapper.getBandMemberDefault(userId);
		if(vo!=null) {
			vo.setDefaultImg(commonMapper.selectImg("BandDef_"+userId));
		}
		return vo;
	}

	@Override
	public int updateMemberDf(BandMemberDefaultVO vo) {
		return bandMemberDefaultMapper.updateMemberDf(vo);
	}

	@Override
	public int insertDefault(BandMemberDefaultVO vo) {
		return bandMemberDefaultMapper.insertDefault(vo);
	}
	
}
