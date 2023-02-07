package co.prjt.own.ownhome.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.ReportVO;
import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.ownhome.service.QuestionVO;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;

@Controller
public class OwnhomeController {

	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	OwnhomeService ownService;
	@Autowired
	ExerRecordMapper exerMapper;
	@Autowired
	ChallengeService challenge;
	@Autowired
	BandService bandService; 
//검색기능 테스트
	@Autowired
	SAccountService SAccountService;
	
	
		//홈으로 이동
		@RequestMapping(value = {"/","/own/home"}, method = RequestMethod.GET)
		public String ownHome(OwnUserVO vo, HttpServletRequest request) { // 오운홈으로 가는 페이지이동
			HttpSession session = request.getSession();
			vo = (OwnUserVO) session.getAttribute("loginUser");
			return "content/own/ownhome";
		}

		// 로그인폼으로 이동
		@RequestMapping(value = "/own/login", method = RequestMethod.GET)
		public String ownLogin(Model model) { // 오운로그인으로..
			return "content/own/ownlogin";
		}

// 검색테스트페이지====================================
		@RequestMapping(value = "/searchtest", method = RequestMethod.GET)
		public String test(Model model) { // 오운홈으로 가는 페이지이동
			return "content/own/searchTest";
		}
		
		@GetMapping("/own/questform")
		public String questform() {
			return "content/own/ownQuest";
		}
		
		
		@GetMapping("/own/sns/ListSearch")
		@ResponseBody
		public List<SAccountVO> ListSearch(){
			return ownService.getSnsAccountList();
		}
		
//검색테스트=======================
		
		// 머지되게해주세요
		// 회원가입 폼으로 이동
		@RequestMapping(value = "/own/SigninForm", method = RequestMethod.GET)
		public String ownSignin(Model model) { // 오운로그인으로..
			String id = passwordEncoder.encode("kyr");
			System.out.println("김유리"+id);
			id = passwordEncoder.encode("admin");
			System.out.println("admin"+id);
			id = passwordEncoder.encode("khj");
			System.out.println("김현지"+id);
			
			return "content/own/ownsignin";
		}
		
		//아이디 중복체크
		@PostMapping("/own/idcheck")
		@ResponseBody
		public int idcheck(String id) {
			System.out.println("-===아이디입니다==="+id);
			int r = ownService.idcheck(id);
			return r;
		}
		
		//내정보수정 비밀번호 체크
		@GetMapping("/own/pwcheck")
		@ResponseBody
		public int pwcheck(String id, String pw, String newpw) {
			OwnUserVO vo = new OwnUserVO();
			System.out.println("입력한 현재 비밀번호 비밀번호==========="+pw);
			newpw = passwordEncoder.encode(newpw);
			System.out.println(id);
			vo = ownService.login(id);
			System.out.println("vo값======"+vo);
			System.out.println(newpw);
			//암호화 된거를 비교하기 위해서 쓰는 match함수
			if(passwordEncoder.matches(pw, vo.getUserPasswd())) {
				
				return 1;
			}
			else
			return 0;
		}
		
		//구비번 신비번 맞을시 진행
		@PostMapping("/own/myinfoupdate")
		@ResponseBody
		public int myupdate(@RequestBody OwnUserVO vo) {
			vo.setUserPasswd(passwordEncoder.encode(vo.getUserPasswd()));
			return ownService.myinfoupdate(vo);
		}
			
		//아이디 찾기
		@GetMapping("/searchId")
		   public String searchId(String email) {
		      System.out.println("넘어오나요 이메일"+email);
		      ownService.sendMail("id",email);
		      return "redirect:/";
		}
		
		@GetMapping("/own/mypage")
		public String myupdate(Model model) {
			return "content/own/ownupdate";
		}
		
		//비밀번호 찾기
		//비밀번호 찾기
		 @GetMapping("/searchPw")
		 @ResponseBody
		   public String searchPw(OwnUserVO vo , String id, String email) {
		      System.out.println("넘어오나요 이메일"+email);
		      System.out.println("넘어온 아이디"+id);
		      vo = ownService.login(id);
		      vo.setUserEmail(email);
		      if(vo.getUserEmail().equals(email)) {
		      System.out.println("아이디 같음");
		      String appNo = ownService.sendMail("PassWord",email);
		      vo.setUserPasswd(passwordEncoder.encode(appNo));
		      ownService.searchPw(vo);
		      return appNo;
		      }
		      return null;
		   }
		//임시비밀번호 전송
		 @PostMapping("/updatepw")
		 @ResponseBody
		   public String updatepw(String emailchk) {
			 System.out.println(emailchk);
		      return "1";
		   }
		 
		 //이메일인증번호 보내기
		 @GetMapping("/own/singin/emailacc")
		 @ResponseBody
		 public String emailacc(@RequestParam String email) {
			 System.out.println(email);
			 return ownService.sendMail("emailAcc", email);			 
		 }
		
		// 등록
		@PostMapping("/own/userInfo")
		@ResponseBody // 데이터리턴할때 넣어줘야함. 리턴값을 json 변환
		public OwnUserVO insert(@RequestBody OwnUserVO vo) {
			vo.setUserPasswd(passwordEncoder.encode(vo.getUserPasswd()));
			System.out.println("========================" + vo);
			ownService.insertUser(vo);
			return vo;
		}

		//내질문 폼
		@GetMapping("/own/mypage/question")
		public String myquestionForm(HttpServletRequest request, Model model, Paging paging, QuestionVO vo) {
			System.out.println("내질문 폼");
			HttpSession session = request.getSession();
			OwnUserVO ovo = (OwnUserVO) session.getAttribute("loginUser");		
			vo.setUserId(ovo.getUserId());
			model.addAttribute("OList", ownService.getPagingmyQuestlist(vo, paging));
			return "content/own/ownMyQuestion";
		}
		
		@PostMapping("/own/QuestADD")
		public String questAdd(QuestionVO vo) {
			System.out.println(vo);
			ownService.questAdd(vo);
			return "redirect:/own/mypage/question";
		}
		
		
		@ResponseBody		
		@GetMapping("/own/mypage/myquestionAjax")
		public List<QuestionVO> myquestionAjax(HttpServletRequest request,Model model, QuestionVO vo, Paging paging) {
			HttpSession session = request.getSession();
			OwnUserVO ovo = (OwnUserVO) session.getAttribute("loginUser");
			vo.setUserId(ovo.getUserId());
			return ownService.getPagingmyQuestlist(vo,paging);
		}
		
		@PostMapping("/own/admin/myquestDelete")
		@ResponseBody
		public int myquestDelete(String rno) {
			System.out.println(rno);
			return ownService.myquestDelete(rno);
		}
		
		
		//=================================================관리자모드================================================
		//질문목록 페이징처리
		@ResponseBody		
		@GetMapping("/own/admin/QuestionList")
		public List<QuestionVO> ownQuestionListAjax(Model model, QuestionVO vo, Paging paging) {
			System.out.println();
			System.out.println(vo.toString());
			System.out.println(paging.toString());
			//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
			return ownService.getPagingAdQuestlist(vo, paging);
		}
		
		//질문목록 불러오기
		@GetMapping("/own/admin/question")
		public String questionList(Model model, Paging paging,QuestionVO vo) {
			System.out.println("======넘기기전입니다======");
			model.addAttribute("OList", ownService.getPagingAdQuestlist(vo, paging));
			return "content/own/ownAdminQuestion";
		}
		//모든질문 페이징 처리
		@ResponseBody		
		@GetMapping("/own/admin/qustionAjax")
		public List<QuestionVO> qustionAjax(Model model, QuestionVO vo, Paging paging) {
			return ownService.getPagingAdQuestlist(vo, paging);
		}
		
		@ResponseBody		
		@GetMapping("/own/admin/myBandPaging")
		public List<BandVO> myBandPaging(Model model, BandMemberDetailVO vo, Paging paging) {
			paging.setPageUnit(3);
			paging.setPageSize(5);
			return ownService.adminBandCheck(vo, paging);
		}
		
		@ResponseBody		
		@GetMapping("/own/admin/myChallPaging")
		public List<ChallengeVO> myChallPaging(Model model, ChallengeVO vo, Paging paging) {
			paging.setPageUnit(3);// 3개씩보기
			paging.setPageSize(10); // 페이딩 동그라미 3개
			vo.setChallStatus("진행 중");
			List<ChallengeVO> myList = challenge.myPageChall(vo, paging);
			return myList;
		}
		
		//질문 한건조회
		@GetMapping("/own/admin/selectquest")
		@ResponseBody
		public QuestionVO selectQuest(@RequestParam String qno) {
			QuestionVO vo = new QuestionVO();
			vo = ownService.selectQuest(qno);
			return vo;
		}
		
		//답변 업데이트
		@PostMapping("/questionUpdate")
		public String questionUpdate(QuestionVO vo) {
			ownService.questionUpdate(vo);
			return "redirect:/own/admin/question";
		}
		
		//답변 업데이트
		@PostMapping("/reportUpdate")
		public String reportUpdate(ReportVO vo) {
			ownService.reportUpdate(vo);
			return "redirect:/own/admin/question";
		}
		
		//모든 회원보기
		@GetMapping("/own/admin/userList")
		public String getUserList(Model model,Paging paging,OwnUserVO vo) {
			model.addAttribute("OList", ownService.getPagingUserList(vo, paging));
			return "content/own/ownAdminUserList";
		}
		//모든 회원 페이징처리
		@ResponseBody		
		@GetMapping("/own/userListAjax")
		public List<OwnUserVO> ownMemberListAjax(Model model, OwnUserVO vo, Paging paging) {
			System.out.println();
			System.out.println(vo.toString());
			System.out.println(paging.toString());
			//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
			return ownService.getPagingUserList(vo, paging);
		}
		
		//신고 페이지 이동
		@GetMapping("/own/admin/reportList")
		public String reportList(Model model,Paging paging,ReportVO vo) {
			model.addAttribute("RList", ownService.getPagingReportList(vo,paging));
			return "/content/own/ownAdminReport";
		}
		
		
		//신고처리
		@PostMapping("/own/admin/reportAccess")
		@ResponseBody
		public int reportUpdate(String val, String rno, String id) {
			System.out.println("value는 "+val);
			System.out.println("rno는 "+rno);
			System.out.println("id는 "+id);
			ReportVO vo = new ReportVO();
			vo.setReportNo(rno);
			vo.setDereporter(id);
			
			OwnUserVO ovo = new OwnUserVO();
			ovo.setUserId(id);
			//보류상황
			if(val.equals("0")) {
				System.out.println("보류처리");
				vo.setStatus("보류처리");
				ownService.reportUpdate(vo);
				return 0;
			}
			else{
				System.out.println("신고처리");
				vo.setStatus("신고처리");
				ownService.reportUpdate(vo);
				return 1;
			}
		}
		
		//테스트 페이지 이동
		//상세보기로..
		@GetMapping("/own/admin/test")
		public String test(HttpServletRequest request, Model model, @RequestParam String id,@ModelAttribute("paging1") Paging paging,
			@ModelAttribute("paging2") Paging paging2) {
			System.out.println("===아이디가넘어올까요="+id);
			HttpSession session = request.getSession();
			OwnUserVO ovo = (OwnUserVO) session.getAttribute("loginUser");
			ChallengeVO vo2 = new ChallengeVO();
			vo2.setUserId(ovo.getUserId());
			// 6개로 페이징
			BandMemberDetailVO vo = new BandMemberDetailVO();
			vo.setUserId(ovo.getUserId());
			
			paging2.setPageUnit(3);// 3개씩보기
			paging2.setPageSize(5); // 페이딩 동그라미 3개
			vo2.setChallStatus("진행 중");
			List<ChallengeVO> myList = challenge.myPageChall(vo2, paging2);
			
			paging.setPageUnit(3);// 3개씩보기
			paging.setPageSize(5); // 페이딩 동그라미 3개
			List<BandVO> bandList = ownService.adminBandCheck(vo, paging);
			
			model.addAttribute("userId", ovo.getUserId());
			model.addAttribute("CList",	myList);
			model.addAttribute("BList", bandList);
			return "/content/own/test";
		}
		
		@ResponseBody		
		@GetMapping("/own/reportListAjax")
		public List<ReportVO> reportListAjax(Model model, ReportVO vo, Paging paging) {
			//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
			return ownService.getPagingReportList(vo, paging);
		}
		
		//신고 한건조회
		@GetMapping("/own/admin/selectReport")
		@ResponseBody
		public ReportVO selectReport(@RequestParam String rno) {
			System.out.println("===========RNO========"+rno);
			return ownService.selectReport(rno);
		}
	
}
