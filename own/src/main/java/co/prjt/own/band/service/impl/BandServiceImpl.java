package co.prjt.own.band.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMapper;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;

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
	public List<Map<String, Object>> getBandAll(BandVO vo) {
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
}
