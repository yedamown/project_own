package co.prjt.own.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.common.mapper.CommonMapper;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.ExersubVO;
import co.prjt.own.common.service.MultimediaVO;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonMapper commonMapper;
	
	@Override
	public List<ExersubVO> getListExersub() {
		// TODO Auto-generated method stub
		return commonMapper.getListExersub();
	}

	@Override
	public List<MultimediaVO> fileUpload() {
		// TODO Auto-generated method stub
		return commonMapper.fileUpload();
	}

}
