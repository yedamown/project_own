package co.prjt.own.band.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandOptionService;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/band")
public class BandOptionController {

	@Autowired
	BandOptionService bandOptionService;
	@Autowired
	BandMemberDetailService BandMemberDetailService;
	@Autowired
	BandService bandService;
	@Autowired
	CommonService common;

	@GetMapping("/bandGroup/bandOptionMain")
	public String bandOptionMain(Model model, BandVO vo, BandMemberDetailVO dvo) {
		model.addAttribute("memList", bandOptionService.bandManageHome(dvo));
		
		model.addAttribute("count", bandOptionService.bandCount(dvo));

		Map<String, Object> band = bandService.getBand(vo.getBandNo());
		// 밴드키워드 자르기
		ArrayList<String> keyword = new ArrayList<String>();

		if (band.get("bandKeyword") != null) {
			StringTokenizer st = new StringTokenizer((String) band.get("bandKeyword"), "#");
			// 처음은 공백이 나와서.. 하나 버리고 감
			st.nextToken();
			while (st.hasMoreTokens()) {
				keyword.add("#" + st.nextToken());
			}
			model.addAttribute("keyword", keyword);
		} else {
			band.put("bandKeyword", "");
		}

		model.addAttribute("bandInfo", band);
		model.addAttribute("count", bandOptionService.bandCount(dvo));
		return "/content/band2/bandOption";
	}

	// ================================= 밴드 옵션 임시 컨트롤러
	// 밴드 수정페이지로 이동
	@GetMapping("/bandGroup/bandOption")
	public String bandOption(Model model, HttpServletRequest request, BandVO vo) {
		// 임시텍스트
		model.addAttribute("imsi", "임시텍스트 밴드설정");
		return "content/band2/bandOption";
	}

	// 남미주
	// 멤버 관리 페이지로 이동 - 전체 회원 목록
	@GetMapping("/bandGroup/bandMemberManage")
	public String bandMemberManage(Model model, BandMemberDetailVO vo, Paging paging) {
	    model.addAttribute("memberList", bandOptionService.bandOptionGetAllMemberList(vo, paging));
		return "content/band2/bandMemberManage";
	}

	// 가입 대기 중 회원 목록
	@GetMapping("/bandGroup/getWaitingMemberList")
	@ResponseBody
	public List<BandMemberDetailVO> GetWaitingMemberList(BandMemberDetailVO vo, Paging paging) {
		return bandOptionService.bandOptionGetWaitingMemberList(vo, paging);
	}

	// 강퇴된 회원 목록
	@GetMapping("/bandGroup/getkickedMemberList")
	@ResponseBody
	public List<BandMemberDetailVO> GetkickedMemberList(BandMemberDetailVO vo, Paging paging) {
		return bandOptionService.bandOptionGetkickedMemberList(vo, paging);
	}

	// 밴드수정 홈페이지 띄우기
	@GetMapping("/bandGroup/bandManage")
	public String bandManagePage(Model model, BandVO vo) {
		System.out.println("===밴드번호====" + vo);
		model.addAttribute("bandInfo", bandOptionService.bandInfo(vo));
		// 운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		// 샘플이미지 여덟장 실어보내기
		// 밴드 대표이미지 찾기
		model.addAttribute("createImage", common.selectImg(vo.getBandNo()));
		return "/content/band2/bandManage";
	}

	// 밴드 업데이트 처리
	@PostMapping("/bandGroup/bandUpdate")
	public String bandUpdate(@RequestParam MultipartFile[] uploadfile, BandVO vo) {
		MultimediaVO mvo = new MultimediaVO();
		System.out.println(uploadfile);
		mvo = common.selectImg(vo.getBandNo());
		if (mvo != null) {
			common.update(uploadfile, mvo);
		} else {
			String number = vo.getBandNo().substring(4);
			common.upload(uploadfile, number, "BDU_", "BAND");
		}
		System.out.println("수정누름" + vo);
		System.out.println(vo.getMediaServerFile());
		int a = bandOptionService.bandUpdate(vo);
		return "redirect:/own/band/bandGroup?bandNo=" + vo.getBandNo();

	}

	// 밴드위임페이지 이동
	@GetMapping("/bandGroup/bandPass")
	public String bandPass(Model model, BandMemberDetailVO dvo) {
		model.addAttribute("memList", bandOptionService.bandManageHome(dvo));
		return "/content/band2/bandImport";
	}

	// 밴드위임처리
	@PostMapping("/bandGroup/bandPass")
	@ResponseBody
	public int bandPass(@RequestBody BandVO vo) {
		System.out.println(vo + "========vo넘어오나요???????????????====");
		bandOptionService.bandPass(vo);
		return 0;
	}

	@PostMapping("/bandGroup/bandHuman")
	@ResponseBody
	public int bandHuman(String bandNo) {
		System.out.println(bandNo + "넘어와라~~");
		bandOptionService.bandHuman(bandNo);
		return 0;
	}

	@PostMapping("/bandGroup/bandDisHuman")
	@ResponseBody
	public int bandDisHuman(String bandNo) {
		bandOptionService.bandDisHuman(bandNo);
		return 0;
	}
}
