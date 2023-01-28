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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeService;
import co.prjt.own.common.service.OwnLikeService;
import co.prjt.own.common.service.OwnLikeVO;
import co.prjt.own.common.service.ReplyVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;
import co.prjt.own.sns.service.SBoardService;
import co.prjt.own.sns.service.SBoardVO;
import co.prjt.own.sns.service.SFollowService;
import co.prjt.own.sns.service.SFollowVO;
import co.prjt.own.sns.service.StoryService;
import co.prjt.own.sns.service.StoryVO;

@Controller
@RequestMapping("/own")
public class SnsController {
	@Autowired SAccountService snsService;

	@Autowired SBoardService boardService;
	
	@Autowired SFollowService followService;
	
	@Autowired StoryService storyService;
	
	@Autowired OwnhomeService ownService;
	
	@Autowired CommonService common;
	
	@Autowired OwnLikeService likeService;
	

	//1. sns홈으로 이동
	@RequestMapping(value = "/sns", method = RequestMethod.GET)
	public String getSnsUserList(HttpServletRequest request, Model model, SBoardVO svo, OwnUserVO ovo, OwnLikeVO like) {
		//세션 담아주기
		HttpSession session = request.getSession();
		//세션에 강제로 로그인유저 저장하기
		//session.setAttribute("loginUser", ownService.login("kyr"));
		ovo = (OwnUserVO) session.getAttribute("loginUser");
		SAccountVO snsInfo = ownService.snsLogin(ovo.getUserId());
		
			if(boardService.getNowBoardList(snsInfo.getSnsAccountNo())!=null) {	
		     	//팔로우 한 계정의 최신게시글 1개
				List<SBoardVO> list = boardService.getNowBoardList(snsInfo.getSnsAccountNo()); 	
				System.out.println("★★★★★리스트입니다"+list);
				List<StoryVO> storyList = storyService.getNowStoryList(snsInfo.getSnsAccountNo());
				
				//이미지 담을 리스트
				List<MultimediaVO> imgList;
				for (SBoardVO i : list){
					imgList = common.selectImgAll(i.getSnsBoardNo());
					i.setFileList(imgList);
				}
				model.addAttribute("snsFollow", followService.followCount(snsInfo.getSnsNickname())); // sns 팔로우 수 
				model.addAttribute("snsInfo", snsInfo);
				model.addAttribute("nowFeed", list);
				model.addAttribute("storyInfo", storyList);
				System.out.println("받아온 스토리 정보 ----------"+storyList);
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
	public String getSnsUser(HttpServletRequest request, Model model, SFollowVO svo,
							 String snsNickname) {
		HttpSession session = request.getSession();
		
		//세션에 강제로 로그인유저 저장하기
		//session.setAttribute("loginUser", ownService.login("kyr"));
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
		followId = snsService.getSnsUser(nickname).getSnsAccountNo();  //followId = 대상 ID (본인 or 타인)
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
		model.addAttribute("profileImg", common.selectImg(followId)); //상대 프로필 이미지
		return "content/sns/snsFeed"; 
		}

	//2-1. 개인피드 상세보기
	@RequestMapping(value = "/snsFeed", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSnsBoard(SBoardVO vo, String snsBoardNo, OwnLikeVO like, String userId) {
		System.out.println("컨트롤 도착!");
		System.out.println(snsBoardNo);
			Map<String, Object> map = new HashMap<>();
			SBoardVO svo = new SBoardVO();
			
			//한 게시글에 대한 모든 이미지 들고오기
			List<MultimediaVO> list = common.selectImgAll(snsBoardNo);
			//게시글 내용 조회
			svo = boardService.getSnsBoard(snsBoardNo);
			//게시글에 대한 댓글 리스트 들고 오기
			List<ReplyVO> rvo = boardService.getBoardReplyList(snsBoardNo);
			svo.setSnsBoardNo(snsBoardNo);
			
			// 좋아요 조회 해봅니데이
			like.setCategoryNo(snsBoardNo);
			int snsLikeCount = likeService.countLike(like);
			like.setUserId(userId);
			
		    OwnLikeVO likeResult =likeService.getLike(like);
		    System.out.println("댓글조회해봅니다------"+svo);
				map.put("imgList", list);
				map.put("svo", svo);
				map.put("reply", rvo);
				map.put("snsLikeCheck", likeResult);
				map.put("snsLikeCount", snsLikeCount);
				
			
				return map;
	}	
	
	
	//3. 게시글작성
	@PostMapping("/snsWriteFeed")
	public String insertSnsBoard(@RequestParam MultipartFile[] uploadfile,
								 SBoardVO svo, String snsAccountNo) {
		svo.setSnsAccountNo(snsAccountNo);
		boardService.insertSnsBoard(svo);
		common.upload(uploadfile, svo.getSnsBoardNo(), "SBN_","SNS");
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
			return followService.followerCount(nickname);
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
	
	//7. 프로필 입력 및 수정
	@PostMapping("/snsProfile")
	public String updateSnsUser(String snsAccountNo, SAccountVO svo,
							  	   @RequestParam MultipartFile[] uploadProfile) {
		System.out.println("받아온 계정번호 값"+snsAccountNo);
		MultimediaVO imgVO = new MultimediaVO();
		imgVO = common.selectImg(snsAccountNo);
		System.out.println("이미지 있는지 체크"+imgVO);
			if(imgVO != null) { //프로필 이미지가 있는 경우
				common.update(uploadProfile, imgVO);
				svo.setSnsAccountNo(snsAccountNo);
				snsService.updateSnsUser(svo);
			}else { // 프로필 이미지가 없는 경우
				String number = snsAccountNo.substring(4);
				svo.setSnsAccountNo(snsAccountNo);
				snsService.updateSnsUser(svo);
				common.upload(uploadProfile, number, "SAU_","SNS");
			}
			return "redirect:/own/snsFeed";
		}
	
	//8. 스토리 입력
	@PostMapping("/snsStory")
	public String insertStory(StoryVO svo, @RequestParam MultipartFile[] uploadStory, String snsAccountNo) {
		svo.setSnsAccountNo(snsAccountNo);
		storyService.insertStory(svo);
		common.upload(uploadStory, svo.getSnsStoryNo(), "SSN_","SNS");
		return "redirect:/own/snsFeed";
	}
	
	
	//9. 스토리 조회
	@PostMapping("/snsStoryList")
	@ResponseBody
	public Map<String, Object> getStroyList(String snsNickname) {
		Map<String, Object> map = new HashMap<>();
		List<StoryVO> list = storyService.getStoryList(snsNickname);
		List<MultimediaVO> storyImgList = new ArrayList<>();
		String youId= snsService.getSnsUser(snsNickname).getSnsAccountNo();
		MultimediaVO imgList = new MultimediaVO();
		MultimediaVO profileImg = common.selectImg(youId);
			for (StoryVO i : list) {
				if(common.selectImg(i.getSnsStoryNo()) != null){
					imgList = common.selectImg(i.getSnsStoryNo());
					System.out.println(imgList);
					storyImgList.add(imgList);
			  }
		}
		map.put("storyImg", storyImgList);
		map.put("profileImg", profileImg);
		map.put("storyInfo", list);
		return map;
	}
	
	//10. 댓글 입력
	@PostMapping("/snsReply")
	@ResponseBody
	public List<ReplyVO> insertSnsReply(ReplyVO rvo, String replyContent, String userId, String categoryNo) {
		System.out.println("댓글등록 컨트롤러 도착");
		System.out.println(replyContent + "1111111"+userId + "222222222"+categoryNo);
			rvo.setReplyContent(replyContent);
			rvo.setUserId(userId);
			rvo.setCategoryNo(categoryNo);
			
			boardService.insertSnsReply(rvo);
			System.out.println("등록할 댓글 정보" + rvo);
			List<ReplyVO> reply = boardService.getBoardReplyList(categoryNo);
		return reply;
	}
	
	//11. 댓글 삭제
	@PostMapping("/snsReplyDelete")
	@ResponseBody
	public List<ReplyVO> deleteSnsReply(ReplyVO rvo, String replyNo, String categoryNo) {
		System.out.println("삭제 컨트롤러 도착");
		System.out.println(replyNo);
			String delReply = "SRE_"+ replyNo;
			rvo.setReplyNo(delReply);
			boardService.deleteSnsReply(rvo);
			List<ReplyVO> reply = boardService.getBoardReplyList(categoryNo);
		return reply;
		
	}
	
	//12. sns 좋아요
	@PostMapping("/snsLike")
	@ResponseBody
	public int addLike(OwnLikeVO like, String snsBoardNo, String userId) {
		like.setCategoryNo(snsBoardNo);
		like.setUserId(userId);
		like.setCategory("SNS");
		
		int result = likeService.addLike(like);
		int snsLikeCount = likeService.countLike(like);
		
		if(result == 1) {
			return snsLikeCount;
		}else {
			return 0;
		}
		
		
	}
	
	//12-1. 좋아요 취소
	@PostMapping("/snsLikeDel")
	@ResponseBody
	public int delLike(OwnLikeVO like, String snsBoardNo, String userId) {
		like.setCategoryNo(snsBoardNo);
		
		like.setUserId(userId);
		int result = likeService.delLike(like);
		int snsLikeCount = likeService.countLike(like);
		
		if(result == 1) {
			return snsLikeCount;
		}else {
			return 0;
		}
		
	}
	
	//13. 검색
    @GetMapping("/snsListSearch")
    @ResponseBody
    public List<SAccountVO> ListSearch(){
    	System.out.println("검색 컨트롤 도착");
       return snsService.getSnsUserList(null);
    }
}
