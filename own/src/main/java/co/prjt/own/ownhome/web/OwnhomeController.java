package co.prjt.own.ownhome.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

@Controller
public class OwnhomeController {

	@Autowired
	OwnhomeService ownService;
	@Autowired
	ExerRecordMapper exerMapper;

	// 수정테스트
	// 통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기

	// 홈으로 이동
	@RequestMapping(value = "/own/home", method = RequestMethod.GET)
	public String ownHome(OwnUserVO vo, HttpServletRequest request) { // 오운홈으로 가는 페이지이동
		HttpSession session = request.getSession();
		vo = (OwnUserVO) session.getAttribute("loginUser");
		return "content/own/ownhome";
	}

	// 로그인폼으로 이동
	@RequestMapping(value = "/own/login", method = RequestMethod.GET)
	public String ownLogin(Model model) { // 오운로그인으로..
		return "content/own/ownlogin";
	}
	

	// 로그인 하기
	@PostMapping("/login")
	@ResponseBody // ajax는 무조건
	public int loginPost(@RequestBody OwnUserVO vo, Model model, HttpServletRequest request, RedirectAttributes rttr) {
		OwnUserVO chk = ownService.login(vo.getUserId());


		if (chk.getUserPasswd().equals(vo.getUserPasswd())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", chk);
			session.setAttribute("snsInfo", ownService.snsLogin(vo.getUserId()));
			System.out.println("========================="+ownService.snsLogin(vo.getUserId()));
			return 1;
		} else
			return 0;
	}

	@GetMapping("/own/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/own/home";
	}

	// 테스트페이지
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) { // 오운홈으로 가는 페이지이동
		return "content/own/test";
	}

	// 머지되게해주세요
	// 회원가입 폼으로 이동
	@RequestMapping(value = "/own/SigninForm", method = RequestMethod.GET)
	public String ownSignin(Model model) { // 오운로그인으로..
		return "content/own/ownsignin";
	}
	
	//아이디 중복체크
	@PostMapping("/own/idcheck")
	@ResponseBody
	public int idcheck(String id) {
		System.out.println("-===아이디입니다==="+id);
		int r = ownService.idcheck(id);
		return r;
	}
	
	// 등록
	@PostMapping("/own/userInfo")
	@ResponseBody // 데이터리턴할때 넣어줘야함. 리턴값을 json 변환
	public OwnUserVO insert(@RequestBody OwnUserVO vo) {
		System.out.println("========================" + vo);
		ownService.insertUser(vo);
		return vo;
	}

	/*
	 * // 오운완(나의운동기록하기) 페이지 이동
	 * 
	 * @RequestMapping(value = "/own/ownRecordForm", method = RequestMethod.GET)
	 * public String ownRecordForm() { return "content/own/ownRecordForm"; }
	 * 
	 * // 오운완(나의운동기록) 등록
	 * 
	 * @PostMapping("/own/exerciseRecord")
	 * 
	 * @ResponseBody public ExerRecordVO exerciseRecord(ExerRecordVO vo,
	 * HttpServletRequest request) { HttpSession session = request.getSession();
	 * OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser"); String id =
	 * user.getUserId(); vo.setUserId(id); System.out.println(vo);
	 * exerMapper.insertExerRecord(vo); return vo; }
	 * 
	 * // 오운완(나의운동기록보기) 페이지 이동
	 * 
	 * @RequestMapping(value = "/own/ownRecordList", method = RequestMethod.GET)
	 * public String ownRecordList(HttpServletRequest request, Model model) {
	 * HttpSession session = request.getSession(); OwnUserVO user = (OwnUserVO)
	 * session.getAttribute("loginUser"); // 세션에 담긴 아이디로 해당 회원의 가장 최신날짜 운동기록 가져오기
	 * model.addAttribute("lRecord", exerMapper.LatestExerRecord(user.getUserId()));
	 * return "content/own/ownRecordList"; }
	 * 
	 * // 세션에 담긴 아이디로 해당 회원의 가장 최신날짜 기록의 갯수 가져오기
	 * 
	 * @GetMapping("/")
	 * 
	 * @ResponseBody public List<ExerRecordVO> dayChart(Model model,
	 * HttpServletRequest request) { HttpSession session = request.getSession();
	 * OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
	 * List<ExerRecordVO> count = exerMapper.DayRecordCounting(user.getUserId());
	 * model.addAttribute("ECount", count); return count; }
	 */
	
}
