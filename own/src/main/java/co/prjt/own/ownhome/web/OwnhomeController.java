package co.prjt.own.ownhome.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.OwnUserVO;


@Controller
public class OwnhomeController {

	@Autowired
	OwnhomeMapper ownMapper;
	 //수정테스트
	 //통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기
	
	//홈페이지 채우기
	@RequestMapping(value = "/own/home", method = RequestMethod.GET)
	public String ownHome(Model model) { //오운홈으로 가는 페이지이동
		model.addAttribute("empList", ownMapper.getEmpList(null));
		return "content/own/ownhome"; 
	}
	
	
	//로그인폼으로 이동
	@RequestMapping(value = "/own/login", method = RequestMethod.GET)
	public String ownLogin(Model model) { // 오운로그인으로..
		return "content/own/ownlogin"; 
	}
	//로그인 하기
	
	@PostMapping("login")
	public String loginPost(String id, String pw, HttpServletRequest request, RedirectAttributes rttr) {
		
		
		
		return null;
	}
	
	//테스트
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String test(Model model) { //오운홈으로 가는 페이지이동
//		return "fragments/header"; 
//	}
	
	//회원가입
	@RequestMapping(value = "/own/userInfo", method = RequestMethod.POST)
	//post면서 json을 담아올 떄는 requestBody를 이용해서 받아와야함
		public String insert(@RequestBody OwnUserVO vo) {
		ownMapper.insertUser(vo);
		return "content/own/ownhome";
	}
	
	
	
}
