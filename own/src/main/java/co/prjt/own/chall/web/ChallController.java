package co.prjt.own.chall.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;
import co.prjt.own.chall.service.CMemberService;
import co.prjt.own.chall.service.CMemberVO;
import co.prjt.own.chall.service.CReportService;
import co.prjt.own.chall.service.CReportVO;
import co.prjt.own.chall.service.CResultService;
import co.prjt.own.chall.service.CResultVO;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.chall.service.ValidationService;
import co.prjt.own.chall.service.ValidationVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeService;
import co.prjt.own.common.service.OwnLikeVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

/**
 * 
 * @author sujin
 * 오운_도전 
 *
 */
@Controller
@RequestMapping("/own/chall")
public class ChallController {

	@Autowired
	OwnhomeService ownService;
	@Autowired
	OwnLikeService ownLike;
	@Autowired
	CommonService common;

	@Autowired
	ChallengeService challenge;
	@Autowired
	CMemberListService memberList;
	@Autowired
	CMemberService member;
	@Autowired
	CAmountService amount;
	@Autowired
	ValidationService validation;
	@Autowired
	CResultService result;
	@Autowired
	CReportService report;

	// 홈페이지, 도전리스트
	@GetMapping("/home")
	// @ResponseBody 데이터를 다시 가져올때.. 안붙이면.. 하얀 html에 받아오는 값이뜬다 ㅎ
	public String challHome(Model model, HttpServletRequest request, @ModelAttribute("paging1") Paging paging,
			@ModelAttribute("paging2") Paging paging2, CMemberVO cmb, ChallengeVO vo1, ChallengeVO vo2) {
		/// 로그인 정보
		HttpSession session = request.getSession();
		// session.setAttribute("loginUser", ownService.login("kjk"));
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		System.out.println("===================도전 홈" + user);
		// 페이징정보담은
		//보일갯수 설정하기
		paging.setPageUnit(3);// 3개씩보기
		paging.setPageSize(3); // 페이딩 동그라미 3개
		List<ChallengeVO> cList = challenge.pageChallList(vo1, paging);
		model.addAttribute("popChall", cList);
		System.out.println("======popChall뉴리스트" + cList);
		// 로그인 정보 있는 경우.
		if (user != null) {
			String userId = user.getUserId();
			vo2.setUserId(userId);
			// 도전멤버 체크
			cmb.setUserId(userId);
			CMemberVO cmb2 = new CMemberVO();
			cmb2 = member.challMemCheck(cmb);
			System.out.println("세션 유저 정보" + user);
			System.out.println("검색결과 도전멤버에서 " + cmb2);

			int rs = challenge.countMychall(vo2);
			// 도전멤버없는 경우 모달띄워서 가입.
			if (cmb2 == null) {
				System.out.println("---------------도전에 정보 널 인 경우?!");
				model.addAttribute("joinChall", user);
				// 도전멤버에 있고, 참여중인 도전도 있는 경우 나의 챌린지 건네주기
			} else if (cmb2 != null && rs != 0) {
				// 도전멤버 있는 경우 정보 넘겨주기
				// 나의도전
				System.out.println("---------------도전회원 & 참여중인 도전 유");
				vo2.setUserId(userId);
				// 6개로 페이징
				paging2.setPageUnit(3);// 3개씩보기
				paging2.setPageSize(3); // 페이딩 동그라미 3개
				List<ChallengeVO> myList = challenge.myPageChall(vo2, paging2);
				System.out.println("======마이뉴리스트====" + myList);
				model.addAttribute("myChall", myList);
			} else {
				System.out.println("----------------새로 가입한 피플ㅋ");
			}
		}
		return "content/chall/challHome";
	}

	// 도전회원 가입..
	@PostMapping("/joinChall")
	public String joinChall(@RequestParam List<MultipartFile[]> uploadfile, CMemberVO vo, Model model,
			RedirectAttributes rttr) {
		System.out.println(vo);
		int rs = member.insertCMem(vo);
		String memNo = vo.getMemNo();
		rttr.addFlashAttribute("result", rs);
		System.out.println(memNo);
		if (rs == 1) {
			for (int i = 0; i < uploadfile.size(); i++) {
				common.upload(uploadfile.get(i), memNo, "CMB_", "Chall");
			}
		}
		return "redirect:/own/chall/home";
	}

	// 도전 중복 닉네임 체크
	@GetMapping("/nickCheck")
	@ResponseBody
	public int nickCheck(@RequestParam("nick") String nickName) {
		int r = member.nickCheck(nickName);
		return r;
	}
 
	//전체도전 페이징  - 데이터넘길때 페이지유닛 등 설정해서 보내기
	@GetMapping("/popChallAjax")
	@ResponseBody
	public List<ChallengeVO> popChallAjax(Paging paging, ChallengeVO vo) {
		List<ChallengeVO> cList = challenge.pageChallList(vo, paging);
		return cList;
	}

	// 내 도전 페이징 아작스 - 데이터넘길때 페이지유닛 등 설정해서 보내기
	@GetMapping("/myChallAjax")
	@ResponseBody
	public List<ChallengeVO> myChallAjax(HttpServletRequest request, Paging paging, ChallengeVO vo) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.userId;
		vo.setUserId(id);
		List<ChallengeVO> cList = challenge.myPageChall(vo, paging);
		return cList;
	}

	// 검색 후 결과페이지로 이동
	@GetMapping("/searchChall")
	public String searchChall(ChallengeVO vo, Model model, Paging paging) {
		System.out.println(vo);
		//보일갯수 설정하기
		paging.setPageUnit(6);// 3개씩보기
		paging.setPageSize(5); // 페이딩 동그라미 3개
		List<ChallengeVO> list = challenge.pageChallList(vo, paging);
		model.addAttribute("searchList", list);
		return "content/chall/searchResult";
	}

	// 도전등록 폼으로 이동
	@GetMapping("/insertFormChall") // 등록 폼으로 이동
	public String insertChall(Model model) {
		// 도전카테고리 리스트 가져와서 모델로 넘겨주기
		model.addAttribute("exersub", common.getListExersub());
		return "content/chall/insertChall";
	}

	// 도전등록 처리
	@PostMapping("/insertChall")
//	@ResponseBody // 데이터를 반환할때는 무조건 리스폰스바디 넣기
	public String insertProc(@RequestParam List<MultipartFile[]> uploadfile, ChallengeVO vo, Model model,
			RedirectAttributes rttr) {
		System.out.println(vo);
		int rs = challenge.insertChall(vo);
		String cNo = vo.getChallNo();
		rttr.addFlashAttribute("result", rs);
		rttr.addFlashAttribute("challNo", cNo);
		if (rs == 1) {
			for (int i = 0; i < uploadfile.size(); i++) {
				common.upload(uploadfile.get(i), cNo, "CHA_", "Chall");
			}
		}
		return "redirect:/own/chall/insertFormChall";
	}

	// 해당 도전 상세보기 페이지로 이동 처리 + 페이지이동
	@GetMapping("/detailChall")
	public String detailChall(@RequestParam("challNo") String no, ChallengeVO vo, ChallengeVO vo2,
			CMemberVO mem, CMemberListVO memck, OwnLikeVO like, HttpServletRequest request, Paging paging,Model model) {
		System.out.println(no);
		String challNo = "CHA_" + no;
		vo.setChallNo(challNo);
		vo2 = challenge.getChall(vo);
		vo2.setNowMem(memberList.getChallMemNum(challNo));
		// 현재회원 수 구하기
		System.out.println(challenge.getChall(vo).getChallLeader());
		mem.setUserId(challenge.getChall(vo).getChallLeader());
		// 프로필 리더정보
		model.addAttribute("leaderInfo", member.getCMem(mem));
		// 챌린지정보
		model.addAttribute("detailChall", vo2);
		// 미디어에서 검색
		model.addAttribute("challImg", common.selectImgAll(challNo));
		//
		paging.setPageUnit(3);// 3개씩보기
		paging.setPageSize(3); // 페이딩 동그라미 3개
		ChallengeVO vo3 = new ChallengeVO();
		List<ChallengeVO> cList = challenge.pageChallList(vo3, paging);
		System.out.println("새 도전 리스트 ---------------------------------"+cList);
		model.addAttribute("newChall", cList);
		// 나의 가입현황 확인 --로그인 세션이용
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		CMemberVO mymem = new CMemberVO();
		if (user != null) {
			String userId = user.getUserId();
			// user정보있을경우 정보 보내줌
			mymem.setUserId(userId);
			model.addAttribute("myInfo", member.getCMem(mymem));
			// 나의 좋아요 여부 파악하기
			like.setUserId(userId);
			like.setCategoryNo(challNo);
			// 북마크 여부 확인 널이면 없는 것임
			model.addAttribute("myLike", ownLike.getLike(like));
			// 멤버리스트에서 참여번호, 내번호, 그리고 승인인거 검색!
			memck.setUserId(user.getUserId()); // 멤버리스트 회원번호설정
			memck.setChallNo("CHA_" + no); // 도전번호
			memck.setMemStatus("승인"); // 상태가 승인인
			model.addAttribute("memcheck", memberList.getMemCheck(memck)); // 하나라서
			// 가입된 곳들어왔을때의 넘겨줄 데이터들..
		}
		return "content/chall/detailChall";
	}

	// 도전 신청페이지로 이동
	@GetMapping("/applyForm") // 등록 폼으로 이동
	public String applyFormChall(CMemberVO mem, ChallengeVO vo, Model model) {
		System.out.println("도전 정보 가져왔나요" + vo);
		System.out.println("멤버인포" + mem);
		model.addAttribute("detailChall", challenge.getChall(vo));
		model.addAttribute("challImg", common.selectImgAll(vo.getChallNo()));
		model.addAttribute("myInfo", member.getCMem(mem));
		return "content/chall/applyChall";
	}

	// 도전신청처리 -> 멤버리스트에 대기로 등록
	@PostMapping("/applyChall")
	@ResponseBody
	public String addMemList(@RequestBody CMemberListVO vo, Model model) {
		
		// 도전 멤버 대기 리스트에 추가 + 정보 모달 후,
		// 다시 -> 상세페이지 + 대기중일경우 버튼 비활성화
		int rs = memberList.insertMemList(vo);
		System.out.println(vo);
		String memListNo = vo.getMemListNo();
		// 우선 멤버대기리스트 식별번호 넘어가게해줌.
		return memListNo;
	}

	// ------------------------- 인 증 관 련 -----------------------------------------------------------
	// 인증 글 등록
	@PostMapping("/insertVldForm")
	public String insertVld(@RequestParam List<MultipartFile[]> uploadfile, ValidationVO vo, Model model) {
		int rs = validation.insertVld(vo);
		String vldNo = vo.getVldNo();
		System.out.println(vldNo);
		String challNo = vo.getChallNo();
		System.out.println(challNo);
		String cutChall = challNo.substring(4);
		System.out.println(cutChall);
		if (rs == 1) {
			for (int i = 0; i < uploadfile.size(); i++) {
				common.upload(uploadfile.get(i), vldNo, "CVD_", "Chall");
			}
		}
		return "redirect:/own/chall/detailChall?challNo=" + cutChall;
	}

	/// 아작스로 함께? 보내기 나중에 여유되면 도전 ㅎ
	// 인증 사진 등록
	// 내 인증사진

	// 오늘 인증횟수+ 이번 주인증횟수 체크
	@PostMapping("/vldCheckAjax")
	@ResponseBody
	public int vldCheck(@RequestBody ValidationVO vo, ChallengeVO cvo) {
		System.out.println("인증vo에 도전번호 아이디 왔나요 ---------"+vo);
		System.out.println("도전vo에 도전번호 왔나요 ---------"+ vo);
		//이번주 인증체크 가능 1 불가능 0
		int weekcheck = validation.vldWeekCheck(vo);
		// 오늘 인증횟수 체크
		int todayVldcheck = validation.todayVld(vo);
		if(weekcheck == 1 && todayVldcheck == 0) {
			//인증가능
			System.out.println("-------------인증가능-----------");
			return 1;
		} else if (todayVldcheck > 0){
			//오늘 완료. 오늘완료랑 이번주완료 동시인경우 오늘완료로 뜨게.
			System.out.println("-------------오늘완-----------");
			return 2;
		} else {
			System.out.println("-------------이번 주 끝-----------");
			return 3;
		}
	}

	//인증내역 아작스하기
	@GetMapping("/vldPageListAjax")
	@ResponseBody
	public List<ValidationVO> vldPagingAjax(ValidationVO vo, Paging paging){
		List<ValidationVO> list = validation.getVldPageList(vo, paging);
		return list;
	}
	
	//-----------------------  신 고 -------------------------------------
	
	// 인증 신고 등록 아작스
	@PostMapping("/addRptAjax")
	@ResponseBody
	public int addRptAjax(@RequestBody CReportVO vo) {
		System.out.println("===================인증신고===================="+vo);
		int r = report.insertCReport(vo);
		return r;
	}

	// 내가 인증신고했는지 확인
	@GetMapping("/checkRptAjax")
	@ResponseBody
	public int checkRptAjax(String vldNo, String reporter, CReportVO vo) {
		vo.setVldNo(vldNo);
		vo.setReporter(reporter);
		int r = report.checkReport(vo);
		return r;
	}

	// 신고리스트 보기
	@GetMapping("/reportListAjax")
	@ResponseBody
	public List<CReportVO> reportListAjax(CReportVO vo) {
		List<CReportVO> list = report.getCReport(vo);
		return list;
	}

	//신고 처리
	@PostMapping("/reportProcess")
	@ResponseBody
	public String reportProcess(@RequestBody CReportVO vo) {
		System.out.println(vo);
		int rs = report.updateCReport(vo);
		System.out.println(rs);
		if (rs == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
//---------------------------------- 멤버관리관련 -----------------------------------------------------------
	
	// 도전리더 - 멤버리스트 출력
	@GetMapping("/challMemList")
	@ResponseBody
	public List<CMemberListVO> challMemList(@RequestParam("challNo") String no, CMemberListVO vo) {
		vo.setChallNo(no);
		List<CMemberListVO> list = memberList.getMemListAll(vo);
		return list;
	}

	// 멤버리스트 권한 변경 -> 승인/ 거절
	@PostMapping("/challMemAuth")
	@ResponseBody
	public String challMemAuth(@RequestBody CMemberListVO vo) {
		int rs = memberList.updateMemList(vo);
		System.out.println(rs);
		if (rs == 1) {
			return "처리를 완료하였습니다.";
		} else {
			return "처리에 오류가 발생하였습니다.";
		}
	}

	// -------------------------달성률 및 결과처리 관련 -----------------------
	// 도전현황 보기
	// 자기자신 도전횟수 , 도전평균 성공률
	@GetMapping("/vldCountAvgAjax")
	@ResponseBody
	public ValidationVO vldAvgAjax(ValidationVO vo, ValidationVO vo2) {
		vo2.setMyVld(validation.countVld(vo));
		vo2.setMemVldAvg(validation.memVldAvg(vo));
		return vo2;
	}

	// 도전결과 정산처리
	@PostMapping("/challResult")
	@ResponseBody
	public String insertChallResult(@RequestBody CMemberListVO vo, CResultVO rs) {
		// 버튼을 누르면 모든 도전멤버를 가져옴 -> 리스트라서 포문돌리면서 insert해주기
		String challNo = vo.getChallNo();
		vo.setMemStatus("승인");
		int rscount = 0;
		List<CMemberListVO> list = memberList.getMemListAll(vo);
		for (CMemberListVO i : list) {
			String userId = i.getUserId();
			rs.setChallNo(challNo);
			rs.setUserId(userId);
			result.insertCResult(rs);
			rscount++;
		}
		String msg = rscount + "처리완료";
		return msg;
	}

	//
	@GetMapping("/myAvgRs")
	public String myAvgRs(CResultVO vo, HttpServletRequest request) {
		
		return null;
	}
	
	// 마이페이지 - 프로필
	@GetMapping("/mypage")
	public String challMypage(CMemberVO vo, CResultVO vo1, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		vo1.setUserId(id);
		System.out.println(member.getCMem(vo));
		System.out.println("=================" + result.successReward(vo1));
		model.addAttribute("memInfo", member.getCMem(vo));
		model.addAttribute("memResult", result.successReward(vo1));
		return "content/chall/mypageChall";
	}

	//
	@GetMapping("/myInfo")
	public String myInfo(CMemberVO vo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		System.out.println(member.getCMem(vo));
		model.addAttribute("memInfo", member.getCMem(vo));
		return "content/chall/myInfo";
	}
	
	// 마이페이지 프로필 수정
	@PostMapping("/myprofileUpdate")
	@ResponseBody
	public String updateMyprofile(@RequestBody CMemberVO vo) {
		// 데이터수정
		int rs = member.updateCMem(vo);
		System.out.println(rs);
		if (rs == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 마이페이지 내 프로필사진 수정 form으로 보내야함.
	@PostMapping("/updateImg")
	public String updateImg(@RequestParam MultipartFile[] uploadfile, String memNo) {
		// 해당 멀티미디어 검색하기
		System.out.println("---------------번호" + memNo);
		MultimediaVO vo = new MultimediaVO();
		// 하나가 나올테니깐..
		vo = common.selectImg(memNo);
		System.out.println("vo검색결과" + vo);
		// 이미지 등록을 하지 않은 경우 업로드하게..
		if (vo != null) {
			System.out.println("-----------------이미지있음");
			common.update(uploadfile, vo);
		} else {
			String number = memNo.substring(4);
			System.out.println("-----------------이미지없음" + number);
			common.upload(uploadfile, number, "CMB_", "Chall");
		}
		return "redirect:/own/chall/myInfo";
	}

	// -------------------------------------- 예치금관련
	// ----------------------------------------------------
	// 마이페이지 - 내 예치금
	@GetMapping("/myAmount")
	public String challMyAmt(CMemberVO vo1, CAmountVO vo2, Model model, Paging paging,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		System.out.println(id);
		vo1.setUserId(id);
		vo2.setUserId(id);
		System.out.println(id);
		System.out.println(amount.getAmtListPage(vo2, paging));
		// 하나에 같이 담을 수 없는게.. 하나는 리스트고 하나는 내역? VO이라서..ㅠㅠ
		model.addAttribute("memInfo", member.getCMem(vo1));
		model.addAttribute("memAmount", amount.getAmtListPage(vo2, paging));
		return "content/chall/myAmount";
	}
	
	// 마이페이지 - 내 예치금 아작스
	@GetMapping("/myAmtAjax")
	@ResponseBody
	public List<CAmountVO> myAmtAjax(CAmountVO vo, Paging paging) {
		List<CAmountVO> list = amount.getAmtListPage(vo, paging);
		return list;
	}
	
	// 마이페이지 - 예치금 충전페이지 이동
	@GetMapping("/payAmount")
	public String payAmount(CMemberVO vo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		model.addAttribute("memInfo", member.getCMem(vo));
		return "content/chall/payForm";
	}

	// 마이페이지 - 예치금 충전
	@PostMapping("/payAjax")
	@ResponseBody
	public String payAjax(@RequestBody CAmountVO vo, CMemberVO mem) {
		// 결제내역 리스트에 insert하기 --여기오는 경우는 예치금 충전밖에없음
		System.out.println("입력받은 vo--------------" + vo);
		vo.setAmtType("충전");
		// d업데이트하는데 아이디랑 금액 필요함.
		int r = amount.insertAmount(vo, mem);
		System.out.println("인서트된..시퀀스값이 나왔나? vo--------------" + vo);
		if (r > 0) {
			return "충전이 완료되었습니다.";
		} else {
			return "결제 과정에서 오류가 발생하였습니다.";
		}
	}
	
	// 출금 / 도전참가
	@PostMapping("/refundAmt")
	@ResponseBody
	public String refundAmt(@RequestBody CAmountVO vo, CMemberVO mem) {
		// amount_list 추가
		int r = amount.insertAmount(vo, mem);
		if (r > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@PostMapping("/refundToBank")
	public String refundToBank(CAmountVO vo, CMemberVO mem) {
		ResponseEntity<JsonNode> rs = amount.refundMoney(vo);
		System.out.println(rs);
		return "content/chall/myAmount";
	}
	
	
//	// 결제 결과아작스 --> 우선 그냥적용안하고 바로 예치금창으로 이동하게함.
//	@GetMapping("/payResult")
//	public String payResult(@RequestParam("amtNo") String no, CAmountVO vo, Model model) {
//		System.out.println(no);
//		String amtNo = "CAL_" + no;
//		vo.setAmtListNo(amtNo);
//		// 챌린지정보
//		model.addAttribute("payResult", amount.getAmt(vo));
//		return "content/chall/payResult";
//	}

	// 마이페이지 도전정보
	@GetMapping("/myChall")
	public String myPageChall(Model model, HttpServletRequest request, Paging paging, ChallengeVO vo) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.userId;
		vo.setUserId(id);
		paging.setPageUnit(6); //내 도전이라 6개씩 보여줄 것
		paging.setPageSize(3); //페이징 동그라미로 할 거라 4개
		// 6개로 페이징
		vo.setChallStatus("진행 중");
		List<ChallengeVO> cList = challenge.myPageChall(vo, paging);
		model.addAttribute("myChall", cList);
		// 테스트 중~~!!!
		return "content/chall/myChall";
	}

	// 마이페이지 도전 페이징 아작스 
	@GetMapping("/myPageChallAjax")
	@ResponseBody
	public List<ChallengeVO> myPageChallAjax(Model model, Paging paging, ChallengeVO vo) {
		return challenge.myPageChall(vo, paging);
	}

	//마이페이지 - 내가 좋아요한 도전
	// 마이페이지 도전 페이징 아작스
	@GetMapping("/myLikeChallAjax")
	@ResponseBody
	public List<ChallengeVO> myLikeChallAjax(Model model, Paging paging, ChallengeVO vo) {
		return challenge.likeChallPage(vo, paging);
	}
	
	//마이페이지 - 내가 리더인..
	// 마이페이지 도전 페이징 아작스
	@GetMapping("/leaderChallAjax")
	@ResponseBody
	public List<ChallengeVO> leaderChallAjax(Model model, Paging paging, ChallengeVO vo) {
		return challenge.pageChallList(vo, paging);
	}
}