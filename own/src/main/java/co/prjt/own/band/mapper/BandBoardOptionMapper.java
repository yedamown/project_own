package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandBoardOptionVO;


public interface BandBoardOptionMapper {
	public List<BandBoardOptionVO> getBandBoardList(String bandNo);

}
