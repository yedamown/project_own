package co.prjt.own.band.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDefaultController {
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired BandMemberDetailService bandMemberDetailService;
	@Autowired CommonService common;
	
	@PutMapping("/up")
	@ResponseBody
	public BandMemberDefaultVO updateMemberDf(@RequestBody BandMemberDefaultVO vo) {
		bandMemberDefaultService.updateMemberDf(vo);
		return vo;
	}
	//밴드멤버 디폴트등록
	@PostMapping("/insertDefault")
	public String insertDefault(@RequestParam MultipartFile deImg[], BandMemberDefaultVO vo, RedirectAttributes rttr, HttpServletRequest request) {
		System.out.println(vo.toString());
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String userId = user.getUserId();
		vo.setUserId(userId);
		int r = bandMemberDefaultService.insertDefault(vo);
		//이미지등록
		if(r>0 && deImg[0].getSize()>0) {
			String res = common.upload(deImg, userId, "BandDef_", "Band");
			System.out.println(res);
		} else { //기본이미지 등록
			bandMemberDetailService.bandProfilImg("BandDef_"+userId);
		}
		return "redirect:/own/band/";
	}
}
