package co.prjt.own.band.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;

@Service
public class BandServiceImpl implements BandService{
	
	@Autowired BandMapper bandMapper;
	
	@Override
	public int insertBand(BandVO vo) {
		return bandMapper.insertBand(vo);
	}

	@Override
	public int updateBand(BandVO vo) {
		return bandMapper.updateBand(vo);
	}

	@Override
	public List<Map<String, Object>> getBandAll(BandVO vo, Paging paging) {
		paging.setTotalRecord(bandMapper.count(vo));
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return bandMapper.getBandAll(vo);
	}

	@Override
	public int count(BandVO vo) {
		return bandMapper.count(vo);
	}

	@Override
	public BandVO getBand(BandVO vo) {
		return bandMapper.getBand(vo);
	}

	@Override
	public List<BandVO> getMemberBandAll(BandMemberDetailVO vo) {
		return bandMapper.getMemberBandAll(vo);
	}

	@Override
	public List<Map<String, Object>> getBandRecentAll(BandMemberDetailVO vo) {
		return bandMapper.getBandRecentAll(vo);
	}

	@Override
	public List<Map<String, Object>> getMyBandAll(BandVO band, Paging paging) {
		//페이지 3개로 임시..
		paging.setPageUnit(3);
		//임시 5..
		paging.setPageSize(5);
		//페이징용 개인의 가입밴드 수
		BandMemberDetailVO detail = new BandMemberDetailVO();
		detail.setUserId("hjj");
		//전체 페이지계산
		paging.setTotalRecord(bandMapper.count2(detail));
		band.setFirst(paging.getFirst());
		band.setLast(paging.getLast());
		//
		return bandMapper.getMyBandAll(band);
	}

	@Override
	public int count2(BandMemberDetailVO vo) {
		return bandMapper.count2(vo);
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
}
