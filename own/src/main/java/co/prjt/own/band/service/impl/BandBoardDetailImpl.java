package co.prjt.own.band.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardDetailVO;
import co.prjt.own.band.service.BandVO;
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
		vo.setFive(vo.getMulti());
		vo.setOne(vo.getMulti());
		//다음 페이지 저장..
		vo.setMulti(vo.getMulti()+1);
		return bandBoardDetailMapper.getFiveBoard(vo);
	}

	@Override
	public List<OwnLikeVO> getOwnLike(@Param(value = "String") List<String> categoryNos, @Param(value = "userId") String userId) {
		return bandBoardDetailMapper.getOwnLike(categoryNos, userId);
	}
}
