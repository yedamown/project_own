package co.prjt.own.ownhome.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordVO;
import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
public class OwnhomeController {

	@Autowired
	OwnhomeMapper ownMapper;
	ExerRecordMapper exerMapper;

	// 수정테스트
	// 통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기

	// 홈페이지 채우기

	// 수정테스트
	// 통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기

	//홈으로 이동
	// 홈으로 이동
	@RequestMapping(value = "/own/home", method = RequestMethod.GET)
	public String ownHome(OwnUserVO vo ,HttpServletRequest request) { // 오운홈으로 가는 페이지이동
	      HttpSession session = request.getSession();
	      vo = (OwnUserVO) session.getAttribute("loginUser");

	      System.out.println(vo);
	      return "content/own/ownhome";
	   }
	
	//로그인폼으로 이동

	@RequestMapping(value = "/own/login", method = RequestMethod.GET)
	public String ownLogin(Model model) { // 오운로그인으로..
		return "content/own/ownlogin";
	}

	// 로그인 하기
	@PostMapping("/login")
	@ResponseBody // ajax는 무조건
	public int loginPost(@RequestBody OwnUserVO vo, Model model, HttpServletRequest request, RedirectAttributes rttr) {
		OwnUserVO chk = ownMapper.login(vo.getUserId());
		
		if(chk.getUserPasswd().equals(vo.getUserPasswd())) {
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", chk);
		session.setAttribute("snsNickname", ownMapper.snsLogin(vo.getUserId()));
		return 1;
		}
		else
		return 0;
	    }

	// 테스트페이지
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) { // 오운홈으로 가는 페이지이동
		return "content/own/test";
	}

	// 회원가입 폼으로 이동
	@RequestMapping(value = "/own/SigninForm", method = RequestMethod.GET)
	public String ownSignin(Model model) { // 오운로그인으로..
		return "content/own/ownsignin";
	}

	// 등록
	@PostMapping("/own/userInfo")
	@ResponseBody // 데이터리턴할때 넣어줘야함. 리턴값을 json 변환
	public OwnUserVO insert(@RequestBody OwnUserVO vo) {
		System.out.println("========================" + vo);
		ownMapper.insertUser(vo);
		return vo;
	}

	// 오운완(나의운동기록) 페이지 이동
	@RequestMapping(value = "/own/ownRecordForm", method = RequestMethod.GET)
	public String ownRecordForm(Model model) {
		return "content/own/ownRecordForm";
	}

	// 오운완(나의운동기록) 등록
	@PostMapping("/own/exerciseRecord")
	@ResponseBody 
	public ExerRecordVO exerciseRecord(ExerRecordVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		exerMapper.insertExerRecord(vo);
		return vo;
	}

}
