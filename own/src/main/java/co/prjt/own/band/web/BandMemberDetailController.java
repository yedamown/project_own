package co.prjt.own.band.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.common.mapper.CommonMapper;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDetailController {
	@Autowired
	BandMemberDetailService bandMemberDetailService;
	@Autowired CommonService commonService;
	
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
	
	//밴드 가입시...내 설정 바꿀 때 중복체크
	@RequestMapping("/bandGroup/duplicateChk")
	@ResponseBody
	public int duplicateChk(String bandNo, String nickName) {
		//카운트 된 숫자로 보냄...0이 검색되면 중복되지 않음
		return bandMemberDetailService.duplicateChk(bandNo, nickName);
	}
	//밴드내의 내 설정 업데이트
	@PutMapping("/bandGroup/myOptionUpdate")
	@ResponseBody
	public int myOptionUpdate(HttpServletRequest request, BandMemberDetailVO vo) {
		System.out.println(vo.toString());
		//이미지입력을 안했을 경우.. 이미지 업데이트는 안함
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		vo.setUserId(user.getUserId());
		
		int r = bandMemberDetailService.myOptionUpdate(vo);
		//만약 사용자가 대표이미지를 넣었다면 업데이트 ##수정
		if(r >= 1 && vo.getAttachFile().getSize() > 0) {
			MultimediaVO multiVo = new MultimediaVO();
			multiVo.setIdentifyId(vo.getBandMemberNo());
			
			MultipartFile[] m = new MultipartFile[1];
			m[0]=vo.getAttachFile();
			String res = commonService.update(m, multiVo);
			System.out.println(res);
		}
		//처리가 됨..
		return r;
	}
}
