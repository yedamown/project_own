package co.prjt.own.band.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardDetailVO;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandCalendarDetailVO;
import co.prjt.own.band.service.BandCalendarVO;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandService;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

@Controller
@RequestMapping("/own/band")
public class BandBoardDetailController {
	@Autowired BandService bandService;
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired CommonService common;
	@Autowired BandBoardOptionService bandBoardOptionService;
	@Autowired OwnhomeService ownService;
	@Autowired BandBoardDetailService bandBoardDetailService;
	
	//밴드내 모든 게시판
	//여기부터 별명 고려해서 매퍼쓰기 위에는 추후 수정(userId만 사용해왔음)
	@GetMapping("/bandGroup/bandBoardDetail")
	public String bandBoardDetail(Model model, HttpServletRequest request, @RequestParam String bandBoardDetailNo) {
		//좋아요 용으로 받아오는 세션
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//세션저장
		session.setAttribute("loginUser", ownService.login("hjj"));
		
		
		BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		vo.setBandBoardDetailNo(bandBoardDetailNo);
		//글단건조회(글+이미지+유저별명) ... 좋아요랑 댓글은 json으로 구현 --> 이미지 뺌
		model.addAttribute("board", bandBoardDetailService.getBandBoardDetail(vo));
		//좋아요 내가 찍었다면 찍었다는 게 필요할 듯..
		//일정있는지 검색해서 넣기(impl)
		//리스트로 만들어서 검색할 수 있는게 있기에 그걸 사용하겠음
		return "content/band/bandBoardDetail";
	}
	//좋아요
	@ResponseBody
	@GetMapping("/bandGroup/bandBoardDetailLike")
	public List<OwnLikeVO> bandBoardDetailLike(String bandBoardDetailNo){
		System.out.println(bandBoardDetailNo);
		return bandBoardDetailService.getOwnDetailLike(bandBoardDetailNo);
	}
	//개인 글쓰기 창으로
	@GetMapping("/bandGroup/bandBoardWrite")
	public String bandBoardWrite(Model model, HttpServletRequest request, String bandNo){
		//좋아요 용으로 받아오는 세션
		HttpSession session = request.getSession();
		//세션저장
		session.setAttribute("loginUser", ownService.login("hjj"));
		//게시판목록
		//밴드 게시판 조회
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
		//말머리도 넣어야 함, 게시판 양식(추후추가)
		return "content/band/bandBoardWrite";
	}
	//개인 글쓰기 창으로
	@ResponseBody
	@PostMapping("/bandGroup/bandBoardInsert")
	public BandBoardDetailSearchVO bandBoardInsert(BandBoardDetailVO board, BandCalendarVO cal) {
		//게시판등록
		System.out.println(board.toString());
		BandBoardDetailSearchVO newBoard = bandBoardDetailService.insertBandBoard(board);
		//일정등록
		if(cal.getBandCalendarTitle()!=null && !(cal.getBandCalendarTitle()).equals("")) {
			cal.setBandBoardDetailNo(newBoard.getBandBoardDetailNo());
			System.out.println(cal.toString());
			//캘린더입력..캘린더 객체로 받음
			newBoard.setBandCalendar(bandBoardDetailService.insertCalendar(cal));
		}
		//투표
		
		//뉴게시판보에 일정넣기
		newBoard.setBandCalendar(cal);
		return newBoard;
	}
	//이미지 업로드...임시 컨트롤러
	@ResponseBody
	@PostMapping("/bandGroup/bandBoardInsertImg")
	public String bandBoardInsertImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		//유저...
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//현재날짜구하기
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		System.out.println(formatter.format(date));
		
		
		//공통 업로드함수가 리스트형태라...리스트로 바꿔주기
		MultipartFile[] uploadfile = new MultipartFile[1];
		uploadfile[0] = file;
		
		//임시번호 이렇게들어가겠네(식별번호)  (MultipartFile[], 밴드번호숫자부분, 밴드번호앞자리(BDU_), "Band")
		//ddMMyyyyHHmmss_hjj  //data+"_"+user.userId  //=>BDI_20230119120322_hjj
		String res = common.upload(uploadfile, date+"_"+user.userId, "BDI_", "Band");
		String mediaServerFile = "";
		System.out.println(res);
		MultimediaVO dbImg = common.selectImg("BDI_"+date+"_"+user.userId);
		mediaServerFile = dbImg.getMediaServerFile(); //실제경로
		System.out.println(mediaServerFile);
		return mediaServerFile; //썸머노트에 뿌릴 값을(이미지의 실제주소..)를 가져감
	}
	@GetMapping("/bandGroup/bandBoardUpdate")
	public String bandBoardUpdate(Model model, HttpServletRequest request, 
			BandBoardDetailSearchVO vo) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//세션저장
		//BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		//vo.setBandBoardDetailNo(BandBoardDetailNo);
		//글단건조회(글+이미지+유저별명) ... 좋아요랑 댓글은 json으로 구현 --> 이미지 뺌 (insert 주석과 같음)
		vo = bandBoardDetailService.getBandBoardDetail(vo);
		model.addAttribute("board", vo);
		//게시판목록
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(vo.getBandNo()));
		return "content/band/bandBoardUpdate";
	}
	//
	@ResponseBody
	@PutMapping("/bandGroup/bandBoardUpdate")
	public BandBoardDetailSearchVO bandBoardUpdate(@RequestBody BandBoardDetailSearchVO vo) {
		//폼에서 들어온 대로 업데이트
		System.out.println("도달함"+ vo.toString());
		//기존에 연결되어있던 이미지.....삭제된거? 추가된거는 쉽게 할 듯
		return bandBoardDetailService.updateBandBoard(vo);
	}
	//지도api
		@GetMapping("/map")
		public String bandMap(Model model) {
			return "content/band/bandMap";
	}
	//일정
	@ResponseBody
	@GetMapping("/bandGroup/CalendarAttend")
	public List<BandCalendarDetailVO> selectCalendarDetail(String bandCalendarNo){
		return bandBoardDetailService.selectCalendarDetail(bandCalendarNo);
	}
	//일정수정
	@ResponseBody
	@GetMapping("/bandGroup/calendarUpdel")
	public List<BandCalendarDetailVO> calendarUpdel(BandCalendarDetailVO vo){
		return bandBoardDetailService.updateCalendarDetail(vo);
	}
	//일정참여 멤버
	@GetMapping("/bandCalendarChk")
	public String bandMap(Model model, String bandAttend, String bandCalendarNo) {
		//참여중인 멤버리스트 
		model.addAttribute("calendarChk" , bandBoardDetailService.selectCalendarDetail(bandCalendarNo));
		model.addAttribute("bandAttend", bandAttend);
		//후에 사진첨가
		return "content/band/bandCalendarChk";
	}
}
