package co.prjt.own.band.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.ownhome.service.OwnhomeService;


@Controller
@RequestMapping("/own/band")
public class BandCalendarController {
	@Autowired BandService bandService;
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired BandMemberDetailService bandMemberDetailService;
	@Autowired CommonService common;
	@Autowired BandBoardOptionService bandBoardOptionService;
	@Autowired OwnhomeService ownService;
	@Autowired BandBoardDetailService bandBoardDetailService;
	
	//밴드 캘린더으로 이동 
	@GetMapping("/bandGroup/bandCalendar")
	public String bandCalendar(Model model, BandVO vo) {
		//임시텍스트
		model.addAttribute("imsi", "임시텍스트 밴드설정");
		return "content/band/bandCalendar";
	}

}