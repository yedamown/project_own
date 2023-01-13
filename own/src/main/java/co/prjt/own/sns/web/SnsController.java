package co.prjt.own.sns.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;
import co.prjt.own.sns.service.SBoardService;
import co.prjt.own.sns.service.SBoardVO;
import co.prjt.own.sns.service.SFollowService;
import co.prjt.own.sns.service.SFollowVO;

@Controller
@RequestMapping("/own")
public class SnsController {
	@Autowired
	SAccountService snsService;

	@Autowired
	SBoardService boardService;
	
	@Autowired
	SFollowService followService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	OwnhomeService ownService;
	
	@Autowired
	CommonService common;
	 //통신 방식이 상관없다면 Request~로 퉁치기. 아니라면 get.. post..정해주기

	//1. sns홈으로 이동
	@RequestMapping(value = "/sns", method = RequestMethod.GET)
	public String getSnsUserList(Model model) {
		model.addAttribute("snsHome", snsService.getSnsUserList(null));
		return "content/sns/snsHome"; 
	}
	
	//1-1. sns 간편 회원 가입
	@PostMapping("/snsAccount")
	public String insertSnsUser(HttpServletRequest request, OwnUserVO ovo, SAccountVO svo, 
								@RequestParam("snsNickname")String snsNickname, @RequestParam("userId")String id) {
		HttpSession session = request.getSession();
		ovo = (OwnUserVO) session.getAttribute("loginUser");
		svo.setSnsNickname(snsNickname);
		snsService.insertSnsUser(svo);
		ownService.updateSnsUser(snsNickname, ovo.getUserId());
		OwnUserVO updateLogin = ownService.login(ovo.getUserId());
		updateLogin.setSnsNickname(snsNickname);
		System.out.println("=kkkkkkkkkkkk========"+updateLogin);
		session.setAttribute("loginUser", updateLogin);
		return "content/sns/snsHome";
	}
	
	//2. 개인피드	
	@RequestMapping(value = "/snsFeed", method = RequestMethod.GET)
	public String getSnsUser(HttpServletRequest request, Model model, SFollowVO svo, OwnUserVO ovo) {
		HttpSession session = request.getSession();
		
		//세션에 강제로 로그인유저 저장하기
		//session.setAttribute("loginUser", ownService.login("kmh"));
		
		ovo = (OwnUserVO) session.getAttribute("loginUser");

		System.out.println("ovo============================="+ovo);
		
		session.setAttribute("snsInfo", ownService.snsLogin(ovo.getUserId()));
		model.addAttribute("snsFeed", boardService.getSnsBoardList(ovo.getUserId())); // sns 개인 피드 게시글 (sns계정식별번호로 조회)
		model.addAttribute("snsFList", followService.getFollowerList(ovo.getSnsAccountNo())); //sns 팔로워 리스트
		model.addAttribute("snsFollower", followService.followerCount(ovo.getSnsAccountNo())); //sns 팔로워 수
		
		//list 에 보드넘버 담아두기
		List<SBoardVO> list = boardService.getSnsBoardNo(ovo.getSnsAccountNo());
		//빈배열생성
		List<MultimediaVO> newList = new ArrayList<>();

		for (SBoardVO i : list){
		    //이미지 개수 구하기 위한 리스트 생성_보드넘버별로 리스트 크기달라서
			if(common.selectImgAll(i.getSnsBoardNo())!=null){
				List<MultimediaVO> imgList = common.selectImgAll(i.getSnsBoardNo());
				if(imgList.size()!=0 ) {
					newList.add(imgList.get(0));
				}
//				    for(int j=0; j<imgList.size(); j++){ 
//				        //새 리스트에 이미지 값 넣기
//				        newList.add(imgList.get(j));
//				    }
			}
		}
		model.addAttribute("snsImg", newList);
		return "content/sns/snsFeed"; 
		}
	
	//2-1. 개인피드 상세보기
	@RequestMapping(value = "/snsDetail", method = RequestMethod.GET)
	public String getSnsBoard(Model model, @RequestParam ("snsNo") String no) {
		model.addAttribute("snsImg", common.selectImgAll("SBN_" + no));
		return null;
	}
	@RequestMapping(value = "/snsFeed2", method = RequestMethod.GET)
	public String getSnsUser2(Model model) {
		model.addAttribute("snsFeed2", boardService.getSnsBoardList(null));
		return "content/sns/snsFeed2"; 
	}
	//3. 파일 업로드
	
	//3. 게시글작성
	@PostMapping("/snsWriteFeed")
	public String insertSnsBoard(HttpServletRequest request, @RequestParam MultipartFile[] uploadfile, SBoardVO svo, OwnUserVO ovo) {
		HttpSession session = request.getSession();
		ovo = (OwnUserVO) session.getAttribute("loginUser");
		svo.setSnsAccountNo(ovo.getSnsAccountNo());
		boardService.insertSnsBoard(svo);
		commonService.upload(uploadfile, svo.getSnsBoardNo(), "SBN_","SNS");
		return "redirect:/own/snsFeed";
	}
}
