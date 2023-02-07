package co.prjt.own.band.web;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardDetailVO;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandCalendarVO;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.ownhome.service.OwnUserVO;
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
		System.out.println(vo.toString());
		model.addAttribute("bandNo", vo.getBandNo());
		return "content/band/bandCalendar";
	}
	//..여기선 커런트데이트로 세팅 다른 곳에선 년도.달로 세팅
	@GetMapping("/bandGroup/bandCalendarNow")
	@ResponseBody
	public List<BandCalendarVO> bandCalendarNow(Model model, BandVO vo, String month) {
		//##########date는 yyyy-MM-dd타입의 스트링으로 보내야 함
		return bandBoardDetailService.selectCalendarNow(vo.getBandNo(), month);
	}
	//일정공유용 새창ㅜㅜ
	//일정에 게시글이 없는 것 게시글insert 기능..캘린더 번호 가져와서 우선 매퍼에 넣고... 매퍼(inpl)에서 vo로 인서트 후 키 반환)
	@GetMapping("/bandGroup/bandCalendarInsert")
	public String bandCalendarInsert(Model model, @Param(value = "bandCalendarNo") String bandCalendarNo, @Param(value = "title")String title, @Param(value = "bandNo")String bandNo) {
		System.out.println(bandCalendarNo+title+bandNo);
		model.addAttribute("bandCalendarNo", bandCalendarNo);
		model.addAttribute("title", title);
		model.addAttribute("bandNo", bandNo);
		model.addAttribute("band", bandService.getBand(bandNo));
		//게시판목록
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
		//일정조회해서 넣기
		model.addAttribute("calendar", bandBoardDetailService.selectCalendar(bandCalendarNo));//리스트가 반환값이므로 인덱스붙여서 보내기
		return "content/band/bandCalendarNewBoard";
	}
	//게시글 업데이트 후에 게시글번호 반환..캘린더 번호 보내서 업데이트도 해야함(게시글번호와 매핑)...캐린더만 있는거 글입력
	@PostMapping("/bandGroup/bandCalendarBoardInsert")
	@ResponseBody
	public BandBoardDetailSearchVO bandCalendarInsert(BandBoardDetailVO vo, HttpServletRequest request) {
		System.out.println(vo.toString());
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//글쓴이
		vo.setBandBoardWriter(user.getUserId());
		//입력하고 업데이트
		return bandBoardDetailService.bandCalendarInsert(vo.getBandCalendarNo(), vo);
	}
	@PostMapping("/insertCalendar")
	@ResponseBody//캘린더만 입력
	public BandCalendarVO insertCalendar(BandCalendarVO vo, HttpServletRequest request) {
		System.out.println(vo.toString());
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//글쓴이
		vo.setUserId(user.getUserId());
		return bandBoardDetailService.insertCalendarSingle(vo);
	}
	@DeleteMapping("/deleteCalendar")
	@ResponseBody
	public String deleteCalendar(String calendarNo) {
		System.out.println(calendarNo);
		return bandBoardDetailService.deleteCalendar(calendarNo);
	}
}