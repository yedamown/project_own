package co.prjt.own.band.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDetailController {
	@Autowired
	BandMemberDetailService bandMemberDetailService;
	
	// 멤버 리스트 페이지 이동
	@RequestMapping(value = "/memberTest", method = RequestMethod.GET)
	public String bandMemberList(BandMemberDetailVO vo, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		vo.setUserId(user.getUserId());
		String bmn = bandMemberDetailService.getBandMemberNo(vo);
		System.out.println("밴드멤버디테일컨트롤러====================="+bmn);
				
		vo.setBandMemberNo(bmn);
		vo.setBandNo(vo.getBandNo());
		model.addAttribute("memberList", bandMemberDetailService.bandMemberList(vo));
		return "content/chat/memberTest";
	}
}
