package co.prjt.own.chall.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;
import co.prjt.own.chall.service.CMemberService;
import co.prjt.own.chall.service.CMemberVO;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.chall.service.ValidationVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own/chall")
public class ChallController {

	@Autowired
	ChallengeService challenge;
	@Autowired
	CMemberListService memberList;
	@Autowired
	CommonService common;
	@Autowired
	CMemberService member;
	@Autowired
	CAmountService amount;


	// 홈페이지, 도전리스트
	@GetMapping("/home")
	public String challHome(Model model, HttpServletRequest request, ChallengeVO vo1, CMemberListVO vo2) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		System.out.println("===================도전 홈"+user);
		if(user != null) {
			//내가 참여중인 진행중인 도전
			//도전정보
			model.addAttribute("challenges", challenge.getMyChall(user.getUserId()));
			//도전번호 뽑아내기 위해 도전들 담아두는 리스트
			List<ChallengeVO> cList = challenge.getMyChall(user.getUserId());
			//멀티미디어 정보 담아둘 새 리스트
			List<MultimediaVO> newList = new ArrayList<MultimediaVO>();
			//도전담아둔 곳에서 도전번호꺼내서 멀티미디어에서 포문돌림.
			for(ChallengeVO i: cList) {
				if(common.selectImgAll(i.getChallNo())!=null){
					List<MultimediaVO> imgList =  common.selectImgAll(i.getChallNo());
					if(imgList.size()!=0) {
						System.out.println(imgList.get(0));
						newList.add(imgList.get(0));
					}
				}
			}
			model.addAttribute("challImg", newList);
		} else {
			System.out.println("===================도전 로그인 x"+user);
			model.addAttribute("challenges", challenge.getChallAll(null));
			List<ChallengeVO> cList = challenge.getChallAll(null);
			List<MultimediaVO> newList = new ArrayList<MultimediaVO>();
			for(ChallengeVO i: cList) {
				if(common.selectImgAll(i.getChallNo())!=null){
					List<MultimediaVO> imgList =  common.selectImgAll(i.getChallNo());
					if(imgList.size()!=0) {
						System.out.println(imgList.get(0));
						newList.add(imgList.get(0));
					}
				}
			}
			model.addAttribute("challImg", newList);
		}
		return "content/chall/challHome";
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
	public String insertProc(@RequestParam List<MultipartFile[]> uploadfile, ChallengeVO vo, Model model, RedirectAttributes rttr) {
		int rs = challenge.insertChall(vo);
		String cNo = vo.getChallNo();
		rttr.addFlashAttribute("result", rs);
		rttr.addFlashAttribute("challNo", cNo);
		if(rs == 1) {
		for (int i = 0; i < uploadfile.size(); i++) {
			common.upload(uploadfile.get(i), cNo, "CHA_", "Chall");
			}
		}
		return "redirect:/own/chall/insertFormChall";
	}
	
	// 해당 도전 상세보기 페이지로 이동 처리 + 페이지이동
	@GetMapping("/detailChall")
	public String detailChall(@RequestParam("challNo") String no, ChallengeVO vo, CMemberVO mem, CMemberListVO memck, HttpServletRequest request, Model model) {
		System.out.println(no);
		vo.setChallNo("CHA_" + no);
		System.out.println(challenge.getChall(vo).getChallLeader());
		mem.setUserId(challenge.getChall(vo).getChallLeader());
		//프로필 리더정보
		model.addAttribute("leaderInfo", member.getCMem(mem));
		String memNo = member.getCMem(mem).getMemNo();
		model.addAttribute("leaderImg", common.selectImg(memNo));
		//챌린지정보
		model.addAttribute("detailChall", challenge.getChall(vo));
		//미디어에서 검색
		model.addAttribute("challImg", common.selectImgAll("CHA_" + no));
		//나의 가입현황 확인 --로그인 세션이용
		HttpSession session = request.getSession();
		
//		session.setAttribute("loginUser", own);
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		
		if(user != null) {
			//멤버리스트에서 참여번호, 내번호, 그리고 승인인거 검색!
			memck.setUserId(user.getUserId()); //멤버리스트 회원번호설정
			memck.setChallNo("CHA_" + no); //도전번호
			memck.setMemStatus("승인"); //상태가 승인인
			model.addAttribute("memcheck", memberList.getMemCheck(memck)); //하나라서
			//가입된 곳들어왔을때의 넘겨줄 데이터들..
		}
		return "content/chall/detailChall";
	}
	
	// 도전 신청페이지로 이동
	@GetMapping("/applyForm") // 등록 폼으로 이동
	public String applyFormChall(@RequestParam("challNo") String no, ChallengeVO vo, Model model) {
		System.out.println(no);
		vo.setChallNo(no);
		model.addAttribute("detailChall", challenge.getChall(vo));
		model.addAttribute("challImg", common.selectImgAll(no));
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
	
	//도전리더 - 멤버리스트 출력
	@GetMapping("/challMemList")
	@ResponseBody
	public List<CMemberListVO> challMemList(@RequestParam("challNo") String no, CMemberListVO vo, Model model) {
		vo.setChallNo(no);
		List<CMemberListVO> list = memberList.getMemListAll(vo);
		return list;
	}

	// 마이페이지 - 프로필
	@GetMapping("/mypage")
	public String challMypage(CMemberVO vo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		System.out.println(member.getCMem(vo));
		String idNo = member.getCMem(vo).getMemNo(); //멤버식별번호
		model.addAttribute("memInfo", member.getCMem(vo));
		model.addAttribute("memImg", common.selectImg(idNo));
		return "content/chall/mypageChall";
	}
	
	// 마이페이지 프로필 수정
	@PostMapping("/myprofileUpdate")
	@ResponseBody
	public String updateMyprofile(@RequestBody CMemberVO vo) {
		//데이터수정
		int rs = member.updateCMem(vo);
		System.out.println(rs);
		if(rs == 1) { 
			return "sucess";
		} else {
			return "fail";
		}
	}

	//마이페이지 내 프로필사진 수정 form으로 보내야함.
	@PostMapping("/updateImg")
	public String updateImg(@RequestParam MultipartFile[] uploadfile, String memNo) {
	//  해당 멀티미디어 검색하기
		System.out.println("---------------번호" + memNo);
		MultimediaVO vo = new MultimediaVO();
		//하나가 나올테니깐..
		vo = common.selectImg(memNo);
		System.out.println("vo검색결과" +vo);
		//이미지 등록을 하지 않은 경우 업로드하게..
		if(vo != null) {
			System.out.println("-----------------이미지있음");
			common.update(uploadfile, vo);
		} else {
			String number = memNo.substring(4);
			System.out.println("-----------------이미지없음" + number);
			common.upload(uploadfile, number, "CMB_", "Chall");
		}
		return "content/chall/challHome";
	}
	
	// 마이페이지 - 내 예치금
	@GetMapping("/myAmount")
	public String challMyAmt(CMemberVO vo1, CAmountVO vo2, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		System.out.println(id);
		vo1.setUserId(id);
		vo2.setUserId(id);
		System.out.println(id);
		System.out.println(amount.getAmountList(vo2));
		// 하나에 같이 담을 수 없는게.. 하나는 리스트고 하나는 내역? VO이라서..ㅠㅠ
		model.addAttribute("memInfo", member.getCMem(vo1));
		model.addAttribute("memAmount", amount.getAmountList(vo2));
		return "content/chall/myAmount";
	}

	// 마이페이지 - 내 도전
	@GetMapping("/myChall") // 처리와 검색을 동시에
	public String myChall(CMemberListVO vo, Model model, HttpServletRequest request) {
		// 세션 아이디로 도전들 검색 ->동적쿼리로 구분하기
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		model.addAttribute("myChall", memberList.getMemListAll(vo));
		return "content/chall/myChall";
	}
	
	
}
