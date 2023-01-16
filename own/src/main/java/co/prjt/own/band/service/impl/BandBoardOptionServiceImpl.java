package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardOptionMapper;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandBoardOptionVO;

@Service
public class BandBoardOptionServiceImpl implements BandBoardOptionService{
	
	@Autowired BandBoardOptionMapper bandBoardOptionMapper;
	
	@Override
	public List<BandBoardOptionVO> getBoardList(String bandNo) {
		return bandBoardOptionMapper.getBoardList(bandNo);
	}

}
