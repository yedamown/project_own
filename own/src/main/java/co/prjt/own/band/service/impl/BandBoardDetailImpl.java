package co.prjt.own.band.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.OwnLikeVO;


@Service
public class BandBoardDetailImpl implements BandBoardDetailService{
	@Autowired BandBoardDetailMapper bandBoardDetailMapper;
	
	@Override
	public int countBandBoard(String bandNo) {
		return bandBoardDetailMapper.countBandBoard(bandNo);
	}

	@Override
	public List<BandBoardDetailSearchVO> getFiveBoard(BandBoardDetailSearchVO vo) {
		return bandBoardDetailMapper.getFiveBoard(vo);
	}

	@Override
	public List<OwnLikeVO> getOwnLike(@Param(value = "String") List<String> categoryNos, @Param(value = "userId") String userId) {
		return bandBoardDetailMapper.getOwnLike(categoryNos, userId);
	}

	@Override
	public List<BandBoardDetailSearchVO> getBandBoard(BandBoardDetailSearchVO vo, Paging paging) {
		paging.setTotalRecord(bandBoardDetailMapper.getBandBoardCount(vo));
		paging.setEndPage(15);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		List<BandBoardDetailSearchVO> list = bandBoardDetailMapper.getBandBoard(vo);
		list.get(0).setBandNo(vo.getBandNo());
		return list;
	}

	@Override
	public int getBandBoardCount(BandBoardDetailSearchVO vo) {
		return bandBoardDetailMapper.getBandBoardCount(vo);
	}
}
