package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.chall.mapper.ValidationMapper;
import co.prjt.own.chall.service.ValidationService;
import co.prjt.own.chall.service.ValidationVO;

public class ValidationServiceImpl implements ValidationService{

	@Autowired ValidationMapper mapper;
	
	@Override
	public int insertVld(ValidationVO vo) {
		return 0;
	}

	@Override
	public int updateVld(ValidationVO vo) {
		return 0;
	}

	@Override
	public ValidationVO getVld(ValidationVO vo) {
		return null;
	}

	@Override
	public List<ValidationVO> getVldAll(ValidationVO vo) {
		return null;
	}

}
