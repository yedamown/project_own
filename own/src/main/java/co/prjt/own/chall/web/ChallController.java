package co.prjt.own.chall.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;
import co.prjt.own.chall.service.CMemberService;
import co.prjt.own.chall.service.CMemberVO;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/chall")
public class ChallController {
	
	@Autowired ChallengeService challenge;
	@Autowired CMemberListService memberList;
	@Autowired CommonService common;
	@Autowired CMemberService member;
	@Autowired CAmountService amount;
	
	//홈페이지, 도전리스트
	@GetMapping("/home")
	public String challHome(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user =(OwnUserVO) session.getAttribute("loginUser");
		System.out.println(user);
		model.addAttribute("challenges", challenge.getChallAll(null)); 
		return "content/chall/challHome";
	}
	
	//도전등록 폼으로 이동
	@GetMapping("/insertFormChall") //등록 폼으로 이동
	public String insertChall(Model model) {
		//도전카테고리 리스트 가져와서 모델로 넘겨주기
		model.addAttribute("exersub", common.getListExersub());
		return "content/chall/insertChall";
	}
	
	//도전등록 처리
	@PostMapping("/insertChall")
	@ResponseBody//데이터를 반환할때는 무조건 리스폰스바디 넣기
	public String insertProc(@RequestBody ChallengeVO vo) {
		challenge.insertChall(vo);
		String cNo = vo.getChallNo(); 
		return cNo;
	}
	
	//해당 도전 상세보기 페이지로 이동 처리 + 페이지이동
	@GetMapping("/detailChall")
	public String detailChall(@RequestParam("challNo") String no, ChallengeVO vo, Model model){
		vo.setChallNo("CHA_" + no);
		model.addAttribute("detailChall", challenge.getChall(vo)); 
		return "content/chall/detailChall";
	}
	
	//도전 신청페이지로 이동
	@GetMapping("/applyForm") //등록 폼으로 이동
	public String applyFormChall(@RequestParam("challNo") String no, ChallengeVO vo,Model model) {
		System.out.println(no);
		vo.setChallNo(no);
		model.addAttribute("detailChall", challenge.getChall(vo));
		return "content/chall/applyChall";
	}
	
	//도전신청처리 -> 멤버리스트에 대기로 등록
	@PostMapping("/addMemList")
	public String addMemList(CMemberListVO vo, Model model) {
		//도전 멤버 대기 리스트에 추가 + 정보 모달 후, 
		// 다시 -> 상세페이지 + 대기중일경우 버튼 비활성화
		memberList.insertMemList(vo);
		System.out.println(vo);
		model.addAttribute("applyMem", memberList.insertMemList(vo));
		return "content/chall/detailChall";
	}
	
	//마이페이지 - 프로필
	@GetMapping("/mypage")
	public String challMypage(CMemberVO vo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		System.out.println(id);
		vo.setUserId(id);
		System.out.println(member.getCMem(vo));
		model.addAttribute("memInfo", member.getCMem(vo));
		return "content/chall/mypageChall";
	}

	//마이페이지 - 내 예치금
	@GetMapping("/myAmount")
	public String challMyAmt(CMemberVO vo1,CAmountVO vo2, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		System.out.println(id);
		vo1.setUserId(id);
		vo2.setUserId(id);
		System.out.println(id);
		System.out.println(amount.getAmountList(vo2));
		//하나에 같이 담을 수 없는게.. 하나는 리스트고 하나는 내역? VO이라서..ㅠㅠ
		model.addAttribute("memInfo", member.getCMem(vo1));
		model.addAttribute("memAmount", amount.getAmountList(vo2));
		return "content/chall/myAmount";
	}
	//마이페이지 - 내 도전
	@GetMapping("/myChall")
	public String myChall(CMemberListVO vo, Model model, HttpServletRequest request) {
		/* model.addAllAttributes("myChall", ) */
		return "content/chall/myChall";
	}
}
