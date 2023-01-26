package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CAmountMapper;
import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;

@Component
public class CAmountServiceImpl implements CAmountService{

	@Autowired CAmountMapper mapper;
	
	@Override
	public int insertAmount(CAmountVO vo) {
		return mapper.insertAmount(vo);
	}

	//단건조회
	@Override
	public CAmountVO getAmt(CAmountVO vo) {
		return mapper.getAmt(vo);
	}
	
	
	@Override
	public List<CAmountVO> getAmountListAll() {
		return mapper.getAmountListAll();
	}

	@Override
	public List<CAmountVO> getAmountList(CAmountVO vo) {
		return mapper.getAmountList(vo);
	}


}
