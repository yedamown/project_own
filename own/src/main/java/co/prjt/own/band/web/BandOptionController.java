package co.prjt.own.band.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandOptionService;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;

@Controller
@RequestMapping("/own/band")
public class BandOptionController {

	@Autowired
	BandOptionService bandOptionService;
	@Autowired 
	BandService bandService;
	@Autowired
	CommonService common;
	
	
	@GetMapping("/bandGroup/bandOptionMain")
	public String bandOptionMain(Model model , BandVO vo, BandMemberDetailVO dvo) {
		model.addAttribute("memList", bandOptionService.bandManageHome(dvo));
		model.addAttribute("count",bandOptionService.bandCount(vo));
		model.addAttribute("bandInfo",bandOptionService.bandInfo(vo));
		return "/content/band2/bandOption";
	}
  

	// ================================= 밴드 옵션 임시 컨트롤러
	// =================================
	// 밴드 수정페이지로 이동
	@GetMapping("/bandGroup/bandOption")
	public String bandOption(Model model, HttpServletRequest request, BandVO vo) {
		// 임시텍스트
		model.addAttribute("imsi", "임시텍스트 밴드설정");
		return "content/band2/bandOption";
	}

	// 밴드 수정페이지로 이동
	@GetMapping("/bandGroup/bandOption2")
	public String bandOption2(Model model, HttpServletRequest request, BandVO vo) {
		// 임시텍스트
		model.addAttribute("imsi", "임시텍스트 밴드설정");
		return "content/band2/bandOption-member";
	}
	
	
	//밴드수정 홈페이지 띄우기
	@GetMapping("/bandGroup/bandManage")
	public String bandManagePage(Model model,BandVO vo) {
		System.out.println("===밴드번호===="+vo);
		model.addAttribute("bandInfo", bandOptionService.bandInfo(vo));
		//운동종류+관심지역 셀렉트박스		
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		//샘플이미지 여덟장 실어보내기
		//밴드 대표이미지 찾기
		model.addAttribute("createImage", common.selectImg(vo.getBandNo()));
		return "/content/band2/bandManage";
	}
	
	
	//밴드 업데이트 처리
	@PostMapping("/bandGroup/bandUpdate")
	public String bandUpdate(@RequestParam MultipartFile[] uploadfile, BandVO vo) {
		MultimediaVO mvo = new MultimediaVO();
		System.out.println(uploadfile);
		mvo = common.selectImg(vo.getBandNo());
		if(mvo != null) {
			common.update(uploadfile, mvo);
		}else {
		 String number = vo.getBandNo().substring(4);
		 common.upload(uploadfile, number, "BDU_", "BAND");
		}		
		System.out.println("수정누름"+vo);
		System.out.println(vo.getMediaServerFile());
		int a = bandOptionService.bandUpdate(vo);
		return "redirect:/own/band/bandGroup?bandNo="+vo.getBandNo();
		
	}
	
	//밴드위임페이지 이동
	@GetMapping("/bandGroup/bandPass")
	public String bandPass(Model model, BandMemberDetailVO dvo) {
		model.addAttribute("memList", bandOptionService.bandManageHome(dvo));
		return "/content/band2/bandPass";
	}
	
	
}
