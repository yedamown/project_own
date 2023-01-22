package co.prjt.own.sns.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.ReplyVO;
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
	public String getSnsUserList(HttpServletRequest request, Model model, SBoardVO svo, OwnUserVO ovo) {
		//세션 담아주기
		HttpSession session = request.getSession();
		//세션에 강제로 로그인유저 저장하기
		//session.setAttribute("loginUser", ownService.login("kmh"));
		ovo = (OwnUserVO) session.getAttribute("loginUser");
		SAccountVO snsInfo = ownService.snsLogin(ovo.getUserId());
		
		if(boardService.getNowBoardList(snsInfo.getSnsAccountNo())!=null) {	
	     	//팔로우 한 계정의 최신게시글 1개
			List<SBoardVO> list = boardService.getNowBoardList(snsInfo.getSnsAccountNo()); 	
			System.out.println("★★★★★리스트입니다"+list);
			
			//이미지 담을 리스트
			List<MultimediaVO> imgList;
			for (SBoardVO i : list){
				imgList = common.selectImgAll(i.getSnsBoardNo());
				i.setFileList(imgList);
			}
			model.addAttribute("snsFollow", followService.followCount(snsInfo.getSnsNickname())); // sns 팔로우 수 
			model.addAttribute("snsInfo", snsInfo);
			model.addAttribute("nowFeed", list);		
		}
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
	public String getSnsUser(HttpServletRequest request, Model model, SFollowVO svo, String snsNickname) {
		HttpSession session = request.getSession();
		
		//세션에 강제로 로그인유저 저장하기
		session.setAttribute("loginUser", ownService.login("kyr"));
		OwnUserVO ovo = new OwnUserVO();
		ovo =(OwnUserVO) session.getAttribute("loginUser");
		SAccountVO userId = ownService.snsLogin(ovo.getUserId());
		System.out.println("로그인 한 정보에 담긴 아이디입니다"+userId);
		System.out.println(snsNickname);
		String nickname;
		String followId; //상대방 아이디
		String followerId;
		if(snsNickname != null) {
			nickname = snsNickname;
		}else {
			nickname = userId.getSnsNickname();
		}
		
		model.addAttribute("userId", userId); //세션값 
		model.addAttribute("snsInfo", snsService.getSnsUser(nickname)); //해당 닉네임에 대한 sns 계정정보 한건
		model.addAttribute("snsFeed", boardService.getSnsBoardList(nickname)); // sns 개인 피드 게시글
		model.addAttribute("snsFeedCount", boardService.countBoard(nickname)); //sns 게시글 수 
		model.addAttribute("snsFollow", followService.followCount(nickname)); // sns 팔로우 수
		model.addAttribute("snsFollowList", followService.getFollowList(nickname)); // sns 팔로우 리스트
		model.addAttribute("snsFollowerList", followService.getFollowerList(nickname)); //sns 팔로워 리스트
		model.addAttribute("snsFollower", followService.followerCount(nickname)); //sns 팔로워 수
		followId = snsService.getSnsUser(nickname).getSnsAccountNo();
		followerId = userId.getSnsAccountNo();
		model.addAttribute("isCheckFollow", followService.isCheckFollow(followId, followerId));
		//팔로우 상태 체크
		System.out.println("팔로우상태체크체크체크체크"+followService.isCheckFollow(followId, followerId));
		
		//대표이미지 하나만 띄우기 
		//list 에 보드넘버 담아두기
		List<SBoardVO> list = boardService.getSnsBoardNo(nickname);
		//빈배열생성
		List<MultimediaVO> newList = new ArrayList<>();

		for (SBoardVO i : list){
		    //이미지 개수 구하기 위한 리스트 생성_보드넘버별로 리스트 크기달라서
			if(common.selectImgAll(i.getSnsBoardNo())!=null){
				List<MultimediaVO> imgList = common.selectImgAll(i.getSnsBoardNo());
				if(imgList.size()!=0 ) {
					newList.add(imgList.get(0));
				}
			}
		}
		model.addAttribute("snsImg", newList);		
		return "content/sns/snsFeed"; 
		}

	//2-1. 개인피드 상세보기
	@RequestMapping(value = "/snsFeed", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSnsBoard(SBoardVO vo, String snsBoardNo) {
		System.out.println("컨트롤 도착!");
		System.out.println(snsBoardNo);
		Map<String, Object> map = new HashMap<>();
		SBoardVO svo = new SBoardVO();
		List<MultimediaVO> list = common.selectImgAll(snsBoardNo);
		svo = boardService.getSnsBoard(snsBoardNo);
		List<ReplyVO> rvo = boardService.getBoardReplyList(snsBoardNo);
		svo.setSnsBoardNo(snsBoardNo);
		System.out.println("댓글조회해봅니다------"+svo);
		map.put("imgList", list);
		map.put("svo", svo);
		map.put("reply", rvo);
		return map;
	}	
	
	
	//3. 게시글작성
	@PostMapping("/snsWriteFeed")
	public String insertSnsBoard(HttpServletRequest request, @RequestParam MultipartFile[] uploadfile, SBoardVO svo, OwnUserVO ovo,
								 String snsAccountNo) {
		HttpSession session = request.getSession();
		ovo = (OwnUserVO) session.getAttribute("loginUser");
		svo.setSnsAccountNo(snsAccountNo);
		boardService.insertSnsBoard(svo);
		commonService.upload(uploadfile, svo.getSnsBoardNo(), "SBN_","SNS");
		return "redirect:/own/snsFeed";
	}
	
	//4. 게시글삭제
	@PostMapping("/boardDelete")
	public String deleteSnsBoard(String snsBoardNo) {
		System.out.println("삭제 컨트롤러 도착");
		int result = boardService.deleteSnsBoard(snsBoardNo);
		System.out.println(result);
			if(result == 1) {
				return "redirect:/own/snsFeed";
			}else {
				return "fail";
			}
	}
	
	//5. 팔로우
	@PostMapping("/follow")
	@ResponseBody
	public int insertFollow(String snsFollowId, String snsFollowerId, String nickname) {
		int result = followService.insertFollow(snsFollowId, snsFollowerId);
		//팔로우 되는데 ..... 왜 ... 숫자는 오르지 않을까..?
		if(result == 1) {
			return followService.followCount(nickname);
		}else {
			return 0;
		}
	}
	
	//6. 언팔로우
	@PostMapping("/unfollow")
	@ResponseBody
	public int deleteFollow(String snsFollowId, String snsFollowerId, String nickname) {
		int result = followService.deleteFollow(snsFollowId, snsFollowerId);
		if(result ==1) {
			return followService.followCount(nickname); 
		}else {
			return 0;
		}
		
	}
	
}
