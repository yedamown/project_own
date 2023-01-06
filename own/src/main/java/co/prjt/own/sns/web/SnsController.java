package co.prjt.own.sns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.sns.mapper.SAccountMapper;

@Controller
public class SnsController {
	@Autowired
	SAccountMapper snsMapper;
	 //수정테스트
	 //통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기
	
	//1. sns홈으로 이동
	@RequestMapping(value = "/sns", method = RequestMethod.GET)
	public String empList(Model model) {
		model.addAttribute("snsHome", snsMapper.getSnsUserList(null));
		return "content/sns/snsHome"; 
	}
	
	//2. 개인피드로 이동
	
}
