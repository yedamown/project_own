package co.prjt.own.band.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDefaultController {
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired BandMemberDetailService bandMemberDetailService;
	@Autowired CommonService common;
	@Autowired BandService bandService;
	@Autowired CommonService commonService;
	
	@PutMapping("/up")
	@ResponseBody
	public BandMemberDefaultVO updateMemberDf(@RequestBody BandMemberDefaultVO vo) {
		bandMemberDefaultService.updateMemberDf(vo);
		return vo;
	}
	//밴드멤버 디폴트등록
	@PostMapping("/insertDefault")
	public String insertDefault(@RequestParam MultipartFile deImg[], BandMemberDefaultVO vo, RedirectAttributes rttr, HttpServletRequest request) {
		//System.out.println(vo.toString());
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String userId = user.getUserId();
		vo.setUserId(userId);
		int r = bandMemberDefaultService.insertDefault(vo);
		//이미지등록
		if(r>0 && deImg[0].getSize()>0) {
			String res = common.upload(deImg, userId, "BandDef_", "Band");
		//	System.out.println(res);
		} else { //기본이미지 등록
			bandMemberDetailService.bandProfilImg("BandDef_"+userId);
		}
		return "redirect:/own/band/";
	}
	//나의 기본디폴트설정수정하고..이것저것
	// 밴드 디폴트 마이옵션 /myBand
	@GetMapping("/myBandOption")
	public String myBand(Model model, HttpServletRequest request) {
		//개인디폴트설정붙이기
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//내 디폴트정보
		model.addAttribute("useries", bandMemberDefaultService.getBandMemberDefault(user.getUserId()));
		//내 디테일정보+밴드조인
		model.addAttribute("bandies", bandMemberDefaultService.getMyBandOption(user.getUserId()));
		//운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		return "content/band/defaultOptionUp";
	}
	@RequestMapping("/myBandOption/user")
	@ResponseBody
	public BandMemberDefaultVO myBandOptionUser(HttpSession session) {
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//카운트 된 숫자로 보냄...0이 검색되면 중복되지 않음
		return bandMemberDefaultService.getBandMemberDefault(user.getUserId());
	}
	@RequestMapping("/myBandOption/band")
	@ResponseBody
	public List<BandMemberDetailVO> myBandOptionBand(HttpSession session) {
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//카운트 된 숫자로 보냄...0이 검색되면 중복되지 않음
		return bandMemberDefaultService.getMyBandOption(user.getUserId());
	} 
	@RequestMapping("/myBandOption/location")
	@ResponseBody
	public List<Map<String, String>> myBandOptionLocation() {
		return bandService.allLocation();
	}
	@RequestMapping("/myBandOption/exercise")
	@ResponseBody
	public List<Map<String, String>> myBandOptionExercise() {
		return bandService.allExcersie();
	}
	@PutMapping("/myBandOption/defalutOptionUpdate")
	@ResponseBody
	public int defalutOptionUpdate(HttpSession session, BandMemberDefaultVO vo) {
		System.out.println(vo.toString());
		//이미지입력을 안했을 경우.. 이미지 업데이트는 안함
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		vo.setUserId(user.getUserId());
		
		int r = bandMemberDefaultService.updateMemberDf(vo);
		//만약 사용자가 대표이미지를 넣었다면 업데이트 ##수정
		if(r >= 1 && vo.getAttachFile().getSize() > 0) {
			MultimediaVO multiVo = new MultimediaVO();
			multiVo.setIdentifyId("BandDef_"+user.getUserId());
			
			MultipartFile[] m = new MultipartFile[1];
			m[0]=vo.getAttachFile();
			String res = commonService.update(m, multiVo);
			System.out.println(res);
		}
		//처리가 됨..
		return r;
	}
	//가치탈퇴
	@RequestMapping("/myBandOption/leave")
	@ResponseBody
	public int myBandLeave(HttpSession session, String bandNo) {
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//카운트 된 숫자로 보냄...0이 검색되면 중복되지 않음
		return bandMemberDefaultService.myBandLeave(user.getUserId(), bandNo);
	}
	//가치철회
		@RequestMapping("/myBandOption/leave2")
		@ResponseBody
		public int myBandLeave2(HttpSession session, String bandNo) {
			OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
			//카운트 된 숫자로 보냄...0이 검색되면 중복되지 않음
			return bandMemberDefaultService.myBandLeave2(user.getUserId(), bandNo);
		}
}
