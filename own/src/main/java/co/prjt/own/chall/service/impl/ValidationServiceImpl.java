package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ValidationMapper;
import co.prjt.own.chall.service.ValidationService;
import co.prjt.own.chall.service.ValidationVO;

@Component
public class ValidationServiceImpl implements ValidationService{

	@Autowired ValidationMapper mapper;
	
	@Override
	public int insertVld(ValidationVO vo) {
		return mapper.insertVld(vo);
	}

	@Override
	public int updateVld(ValidationVO vo) {
		return mapper.updateVld(vo);
	}

	@Override
	public ValidationVO getVld(ValidationVO vo) {
		return mapper.getVld(vo);
	}

	@Override
	public List<ValidationVO> getChallVld(ValidationVO vo) {
		return mapper.getChallVld(vo);
	}

	@Override
	public List<ValidationVO> getMyVld(ValidationVO vo) {
		return mapper.getMyVld(vo);
	}

	@Override
	public List<ValidationVO> getMyChallVld(ValidationVO vo) {
		return mapper.getMyChallVld(vo);
	}



}
