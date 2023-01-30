package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CReportMapper;
import co.prjt.own.chall.service.CReportService;
import co.prjt.own.chall.service.CReportVO;

@Component
public class CReportServiceImpl implements CReportService{

	@Autowired CReportMapper mapper;

	@Override
	public int insertCReport(CReportVO vo) {
		return mapper.insertCReport(vo);
	}

	@Override
	public int updateCReport(CReportVO vo) {
		return mapper.updateCReport(vo);
	}

	@Override
	public List<CReportVO> getCReport(CReportVO vo) {
		return mapper.getCReport(vo);
	}

	@Override
	public List<CReportVO> getCReportAll(CReportVO vo) {
		return mapper.getCReportAll(vo);
	}

	@Override
	public int checkReport(CReportVO vo) {
		return mapper.checkReport(vo);
	}

}
