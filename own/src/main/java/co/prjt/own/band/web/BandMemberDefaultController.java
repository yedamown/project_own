package co.prjt.own.band.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDefaultController {
	@Autowired
	BandMemberDefaultService bandMemberDefaultService;

	@PutMapping("/up")
	@ResponseBody
	public BandMemberDefaultVO updateMemberDf(@RequestBody BandMemberDefaultVO vo) {
		bandMemberDefaultService.updateMemberDf(vo);
		return vo;
	}
	
}
