package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.chall.mapper.CAmountMapper;
import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;

public class CAmountServiceImpl implements CAmountService{

	@Autowired CAmountMapper mapper;
	
	@Override
	public int insertAmount(CAmountVO vo) {
		return 0;
	}

	@Override
	public List<CAmountVO> getAmountListAll() {
		return null;
	}

	@Override
	public List<CAmountVO> getAmountList() {
		return null;
	}

}
