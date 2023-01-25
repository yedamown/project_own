package co.prjt.own.band.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandCalendarVO;
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
	
	//밴드 캘린더으로 이동 ..여기선 커런트데이트로 세팅 다른 곳에선 년도.달로 세팅
	@GetMapping("/bandGroup/bandCalendar")
	public String bandCalendar(Model model, BandVO vo) {
		model.addAttribute("bandNo", vo.getBandNo());
		return "content/band/bandCalendar";
	}
	//..여기선 커런트데이트로 세팅 다른 곳에선 년도.달로 세팅
	@GetMapping("/bandGroup/bandCalendarNow")
	@ResponseBody
	public List<BandCalendarVO> bandCalendarNow(Model model, BandVO vo, String month) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date now = new Date();
//		String now_dt = format.format(now);
		//##########date는 yyyy-MM-dd타입의 스트링으로 보내야 함
		return bandBoardDetailService.selectCalendarNow(vo.getBandNo(), month);
	}
}