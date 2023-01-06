package co.prjt.own.sns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.sns.mapper.SAccountMapper;
import co.prjt.own.sns.mapper.SBoardMapper;

@Controller
public class SnsController {
	@Autowired
	SAccountMapper snsMapper;
	
	@Autowired SBoardMapper boardMapper;
	
	 //통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기
	
	//1. sns홈으로 이동
	@RequestMapping(value = "/own/sns", method = RequestMethod.GET)
	public String getSnsUserList(Model model) {
		model.addAttribute("snsHome", snsMapper.getSnsUserList(null));
		return "content/sns/snsHome"; 
	}
	
	//2. 개인피드
	@RequestMapping(value = "/own/snsFeed", method = RequestMethod.GET)
	public String getSnsUser(Model model) {
		model.addAttribute("snsFeed", boardMapper.getSnsBoardList(null));
		return "content/sns/snsFeed"; 
	}
	
	
	
	
}
