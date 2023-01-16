package co.prjt.own.band.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailService;


@Service
public class BandBoardDetailImpl implements BandBoardDetailService{
	@Autowired BandBoardDetailMapper bandBoardDetailMapper;
	
	@Override
	public int countBandBoard(String bandNo) {
		return bandBoardDetailMapper.countBandBoard(bandNo);
	}
}
