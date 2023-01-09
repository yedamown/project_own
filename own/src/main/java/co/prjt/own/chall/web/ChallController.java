package co.prjt.own.chall.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;

@Controller
@RequestMapping("/own/chall")
public class ChallController {
	
	@Autowired ChallengeService challenge;
	
	@GetMapping("/insertFormChall") //등록 폼으로 이동
	public String insertChall() {
		return "content/chall/insertChall";
	}
	
	@PostMapping("/insertChall")
	public String insertProc(ChallengeVO vo) {
		challenge.insertChall(vo);
		return "content/chall/challHome";
	}
	
	@GetMapping("/detailChall")
	public String detailChall(ChallengeVO vo){
		return "content/chall/challDetail";
	}
	
	@GetMapping("/home")
	public String challHome(Model model) {
		model.addAttribute("challenges", challenge.getChallAll(null)); 
		return "content/chall/challHome";
	}
	
}
