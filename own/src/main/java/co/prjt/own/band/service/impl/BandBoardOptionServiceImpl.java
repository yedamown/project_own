package co.prjt.own.band.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandBoardDetailMapper;
import co.prjt.own.band.mapper.BandBoardOptionMapper;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandBoardOptionVO;

@Service
public class BandBoardOptionServiceImpl implements BandBoardOptionService{
	
	@Autowired BandBoardOptionMapper bandBoardOptionMapper;
	@Autowired BandBoardDetailMapper bandBoardDetailMapper;
	
	@Override
	public List<BandBoardOptionVO> getBandBoardList(String bandNo) {
		List<BandBoardOptionVO> board = bandBoardOptionMapper.getBandBoardList(bandNo);
		return board;
	}

	@Override
	public int updateBandBoardOption(BandBoardOptionVO vo) {
		return bandBoardOptionMapper.updateBandBoardOption(vo);
	}

	@Override
	public int deleteBandBoardOption(String bandBoardOptionNo) {
		int r = bandBoardOptionMapper.deleteBandBoardOption(bandBoardOptionNo);
		if(r>0) {
			//게시글도 삭제
			bandBoardDetailMapper.deleteBandBoard(bandBoardOptionNo);
		}
		return bandBoardOptionMapper.deleteBandBoardOption(bandBoardOptionNo);
	}

	@Override
	public int insertBandBoardOption(String bandNo) {
		BandBoardOptionVO vo = new BandBoardOptionVO();
		vo.setBandNo(bandNo);
		return bandBoardOptionMapper.insertBandBoardOption(vo);
	}

	@Override
	public int insertBandBoardOptionLine(String bandNo) {
		BandBoardOptionVO vo = new BandBoardOptionVO();
		vo.setBandNo(bandNo);
		return bandBoardOptionMapper.insertBandBoardOptionLine(vo);
	}

	@Override
	public int bandOption9LineUpdate(List<Map<String, String>> obj) {
		int r = 0;
		for(int i=0; i<obj.size(); i++) {
			BandBoardOptionVO vo = new BandBoardOptionVO();
			vo.setBandBoardOptionNo(obj.get(i).get("bandBoardOptionNo"));
			vo.setBandBoardOrder(Integer.parseInt(obj.get(i).get("bandBoardOrder")));
			int y = bandBoardOptionMapper.bandOption9LineUpdate(vo);
			if(y>0) r++;
		}
		return r;
	}

}
