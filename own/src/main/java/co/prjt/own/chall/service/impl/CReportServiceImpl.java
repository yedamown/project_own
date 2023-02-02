package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CReportMapper;
import co.prjt.own.chall.mapper.ValidationMapper;
import co.prjt.own.chall.service.CReportService;
import co.prjt.own.chall.service.CReportVO;
import co.prjt.own.chall.service.ValidationVO;

@Component
public class CReportServiceImpl implements CReportService{

	@Autowired CReportMapper mapper;
	@Autowired ValidationMapper validation;
	
	@Override
	public int insertCReport(CReportVO vo) {
		return mapper.insertCReport(vo);
	}

	//신고처리 업데이트
	@Override
	public int updateCReport(CReportVO vo) {
		String rpSt = vo.getReportState();
		if(rpSt.equals("신고 승인")) {
			String vldNo = vo.getVldNo();
			ValidationVO vld = new ValidationVO();
			vld.setVldNo(vldNo);
			vld.setVldStatus("실패");
			int rs = validation.updateVld(vld);
			System.out.println("인증---vo =========" + vld);
			if(rs == 1) {
				int r = mapper.updateCReport(vo);
				System.out.println("신고"+ vo);
				return r;
			}else {
				return 0;				
			}
		} else {
			int r = mapper.updateCReport(vo);
			return r;
		}
	}

	@Override
	public List<CReportVO> getCReport(CReportVO vo) {
		return mapper.getCReport(vo);
	}

	@Override
	public int checkReport(CReportVO vo) {
		return mapper.checkReport(vo);
	}
	
	//페이징넣을깜?
	@Override
	public List<CReportVO> getCReportAll(CReportVO vo) {
		return null;
	}

}
