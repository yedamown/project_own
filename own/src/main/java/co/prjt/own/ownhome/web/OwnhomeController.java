package co.prjt.own.ownhome.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;


@Controller
public class OwnhomeController {

	@Autowired
	OwnhomeMapper empMapper;
	 //수정테스트
	 //통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기
	@RequestMapping(value = "/empList", method = RequestMethod.GET)
	public String empList(Model model) {
		model.addAttribute("empList", empMapper.getEmpList(null));
		return "empList"; 
	}
	
}
