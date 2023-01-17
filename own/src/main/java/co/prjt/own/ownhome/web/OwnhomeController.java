package co.prjt.own.ownhome.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.encoder.Encoder;
import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.ownhome.service.QuestionVO;

@Controller
public class OwnhomeController {

	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	OwnhomeService ownService;
	@Autowired
	ExerRecordMapper exerMapper;

	// 홈으로 이동
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

		// 테스트페이지
		@RequestMapping(value = "/test", method = RequestMethod.GET)
		public String test(Model model) { // 오운홈으로 가는 페이지이동
			return "content/own/test";
		}

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
			System.out.println(vo);
			return 0;		
		}
			
		//아이디 찾기
		@GetMapping("/searchId")
		   public String searchId(String email) {
		      System.out.println("넘어오나요 이메일"+email);
		      ownService.sendMail("id",email);
		      return "redirect:/";
		   }
		
		@GetMapping("/own/myupdate")
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
		     
		      if(vo.getUserEmail().equals(email)) {
		      System.out.println("아이디 같음");
		     // String appNo = ownService.sendMail("PassWord",email);
		      return "1";
		      }
		      return null;
		   }
		//임시비밀번호 전송
		 @GetMapping("/updatepw")
		 @ResponseBody
		   public String updatepw(String emailchk) {
			 System.out.println(emailchk);
		      return "1";
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


		//=================================================관리자모드================================================
		
		//질문목록 불러오기
		@GetMapping("/own/admin/question")
		public String questionList(Model model) {
			List<QuestionVO> vo = ownService.questionList();
			System.out.println(vo);
			model.addAttribute("QList", vo);
			return "content/own/question";
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
		
	
}
