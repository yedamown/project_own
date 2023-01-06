package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.chall.mapper.CReportMapper;
import co.prjt.own.chall.service.CReportService;
import co.prjt.own.chall.service.CReportVO;

public class CReportServiceImpl implements CReportService{

	@Autowired CReportMapper mapper;
	
	@Override
	public CReportVO insertCreport(CReportVO vo) {
		return null;
	}

	@Override
	public CReportVO updateCreport(CReportVO vo) {
		return null;
	}

	@Override
	public List<CReportVO> getCReport(CReportVO vo) {
		return null;
	}

	@Override
	public List<CReportVO> getCReportAll(CReportVO vo) {
		return null;
	}

}
