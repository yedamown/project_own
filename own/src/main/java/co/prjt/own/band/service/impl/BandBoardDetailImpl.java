package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardDetailVO;


@Service
public class BandBoardDetailImpl implements BandBoardDetailService{
	@Autowired BandBoardDetailMapper bandBoardDetailMapper;
	
	@Override
	public int countBandBoard(String bandNo) {
		return bandBoardDetailMapper.countBandBoard(bandNo);
	}

	@Override
	public List<BandBoardDetailVO> getFiveBoard(BandBoardDetailVO vo) {
		return bandBoardDetailMapper.getFiveBoard(vo);
	}
}
