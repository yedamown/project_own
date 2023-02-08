package co.prjt.own.band.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import co.prjt.own.band.service.BandBoardOptionVO;
import co.prjt.own.band.service.BandCalendarDetailVO;
import co.prjt.own.band.service.BandCalendarVO;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

/**
 * 허진주
 * 보드게시판
 * @author admin
 *
 */
@Controller
@RequestMapping("/own/band")
public class BandBoardDetailController {
	@Autowired BandService bandService;
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired CommonService common;
	@Autowired BandBoardOptionService bandBoardOptionService;
	@Autowired OwnhomeService ownService;
	@Autowired BandBoardDetailService bandBoardDetailService;
	@Autowired BandMemberDetailService bandMemberDetailService;
	
	//밴드내 모든 게시판
	//여기부터 별명 고려해서 매퍼쓰기 위에는 추후 수정(userId만 사용해왔음)
	@GetMapping("/bandGroup/bandBoardDetail")
	public String bandBoardDetail(Model model, HttpSession session, BandBoardDetailSearchVO vo) {
		//좋아요 용으로 받아오는 세션
		//세션저장
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		/*
		 * BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		 * vo.setBandBoardDetailNo(bandBoardDetailNo);
		 */
		//게시판 단건조회 getBandBoardOption
		model.addAttribute("bandBoardOption", bandBoardOptionService.getBandBoardOption(vo.getBandBoardOptionNo()));
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
	public String bandBoardWrite(Model model, HttpSession session, String bandNo){
		//좋아요 용으로 받아오는 세션
		//session.setAttribute("loginUser", ownService.login("hjj"));
		//게시판목록
		//밴드 게시판 조회 //말머리도 넣어야 함, 게시판 양식(추후추가)
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
		//일정넣을 때 밴드번호 넣어야..하네 넣음
		model.addAttribute("bandNo", bandNo);
		//밴드장확인......
		model.addAttribute("band", bandService.getBand(bandNo));
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
	//게시판생성
	@GetMapping("/bandGroup/bandOption9")
	public String bandOption9(Model model, String bandNo) {
		//밴드 게시판 조회
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
		model.addAttribute("bandNo", bandNo);
		return "content/band/zBoardOption";
	} 
	//게시판 단건 수정
	@ResponseBody
	@PutMapping("/bandGroup/bandOption9")
	public List<BandBoardOptionVO> bandOption9(@RequestBody BandBoardOptionVO vo) {
		System.out.println(vo.toString());
		//후에 게시판가져가기
		List<BandBoardOptionVO> boardList = new ArrayList<BandBoardOptionVO>();
		if(bandBoardOptionService.updateBandBoardOption(vo)>0) {
			boardList = bandBoardOptionService.getBandBoardList(vo.getBandNo());
		}
		return boardList;
	}
	//게시판 단건 삭제
	@ResponseBody
	@DeleteMapping("/bandGroup/bandOption9/{bandBoardOptionNo}/{bandNo}")
	public List<BandBoardOptionVO> bandOption9(@PathVariable String bandBoardOptionNo, @PathVariable String bandNo) {
		System.out.println("도착"+bandBoardOptionNo);
		System.out.println("도착"+bandNo);
		int r = bandBoardOptionService.deleteBandBoardOption(bandBoardOptionNo);
		//후에 게시판가져가기
		List<BandBoardOptionVO> boardList = new ArrayList<BandBoardOptionVO>();
		if(r>0) {
			boardList = bandBoardOptionService.getBandBoardList(bandNo);
		}
		return boardList;
	}
	//게시판 구분 생성
	@ResponseBody
	@DeleteMapping("/bandGroup/bandOption9/insert/{bandNo}")
	public List<BandBoardOptionVO> bandOption9Insert(@PathVariable String bandNo) {
		System.out.println("도착"+bandNo);
		int r = bandBoardOptionService.insertBandBoardOption(bandNo);
		//후에 게시판가져가기
		List<BandBoardOptionVO> boardList = new ArrayList<BandBoardOptionVO>();
		if(r>0) {
			boardList = bandBoardOptionService.getBandBoardList(bandNo);
		}
		return boardList;
	}
	//게시판 구분 생성 line
	@ResponseBody
	@DeleteMapping("/bandGroup/bandOption9/insertLine/{bandNo}")
	public List<BandBoardOptionVO> bandOption9InsertLine(@PathVariable String bandNo) {
		System.out.println("도착"+bandNo);
		int r = bandBoardOptionService.insertBandBoardOptionLine(bandNo);
		//후에 게시판가져가기
		List<BandBoardOptionVO> boardList = new ArrayList<BandBoardOptionVO>();
		if(r>0) {
			boardList = bandBoardOptionService.getBandBoardList(bandNo);
		}
		return boardList;
	}
	//게시판순서변경
	@ResponseBody
	@PostMapping("/bandGroup/bandOption9")
	public int bandOption9LineUpdate(@RequestBody List<Map<String, String>> obj) {
		return bandBoardOptionService.bandOption9LineUpdate(obj);
	}

	//밴드내 게시판 이동
	@GetMapping("/bandGroup/bandBoardList")
	public String bandMainGroup(Model model, HttpServletRequest request, BandBoardDetailSearchVO vo, Paging paging) {
		//검색어가 있다면 저장해놓기searchValue
		model.addAttribute("searchValue", vo.getSearchValue());
		//세션에 밴드저장해놓자..
		if(vo.getBandNo()!=null) {
			Map<String,Object> band =  bandService.getBand(vo.getBandNo());
			model.addAttribute("band", band);
			vo.setBandNo((String) band.get("bandNo"));
		}
		if(vo.getBandNo()==null||vo.getBandNo().equals("")) {
			//단건 삭제후 바로 이동하려니 밴드번호를 못 가져오네... 글번호로 검색하기
			BandVO band2 =  bandService.getBandByBoardOptionNo(vo.getBandBoardOptionNo());
			model.addAttribute("band", band2);
			vo.setBandNo(band2.getBandNo());
		}
		System.out.println(vo.toString());
		//밴드번호+게시판번호를 가져오면 모든 글과...페이징처리해서보냄(1페이지만 페이지처리방식)
		model.addAttribute("boardList", bandBoardDetailService.getBandBoard(vo, paging));
		//전체게시판이면 all
		model.addAttribute("bandBoardOptionNo", vo.getBandBoardOptionNo());
		model.addAttribute("bandNo", vo.getBandNo());
		//밴드장이면 삭제가 가능하니 밴드장확인
		//밴드옵션가져오기
		if(vo.getBandBoardOptionNo()!=null) {// null 은 모든게시판
			model.addAttribute("bandBoardOption", bandBoardOptionService.getBandBoardOption(vo.getBandBoardOptionNo()));
		}
		//밴드장인지 확인(삭제게시판이동을위해)
		return "content/band/bandBoardList";
	}
	
	//Ajax//밴드내 모든 게시판//위와 세트
	@ResponseBody
	@GetMapping("/bandGroup/bandBoardListAjax")
	public List<BandBoardDetailSearchVO> bandMainGroupAjax(Model model, HttpServletRequest request, BandBoardDetailSearchVO vo, Paging paging) {
		System.out.println(vo.toString());
		System.out.println("도착");
		System.out.println(paging.toString());
		//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
		return bandBoardDetailService.getBandBoard(vo, paging);
	}
	
	// 밴드 글(리스트) 삭제
	@ResponseBody
	@PostMapping("/bandGroup/boardDelete")
	public List<BandBoardDetailSearchVO> boardDelete(@RequestBody List<Object> obj) {
		//obj[0]=글번호//obj[1]페이지//obj[2]게시판번호//bnadNo[3]
		System.out.println(obj);
		BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		System.out.println(obj.get(0));
		ArrayList<String> bandBoardDetailNos = (ArrayList<String>) obj.get(0);
		vo.setBandBoardDetailNos(bandBoardDetailNos);
		int r = bandBoardDetailService.BandBoardDeleteList(vo);
		if(r>0) {
			//밴드번호를 가져오면 모든 글과...페이징처리해서보냄 paging번호받
			Paging paging = new Paging();
			Integer p = Integer.parseInt((String.valueOf(obj.get(1))));
			paging.setPage(p);
			String bandBoardOptionNo =  (String) obj.get(2);
			vo.setBandBoardOptionNo(bandBoardOptionNo);
			String bandNo =  (String) obj.get(3);
			vo.setBandNo(bandNo);
			return bandBoardDetailService.getBandBoard(vo, paging);
		}
		return null;
	}
	//밴드 글 단건삭제
	//주의 bandBoardOptionNo로 오지만 사실 banbBoardDetailNo임
	@ResponseBody
	@GetMapping("/bandGroup/bandBoardDeleteEach")
	public int bandBoardDeleteEach(HttpSession session, String bandBoardOptionNo) {
		//session.setAttribute("loginUser", ownService.login("hjj"));
		//return "redirect:/own/band/bandGroup?bandNo=BDU_"+band.getBandNo();
		return bandBoardDetailService.deleteBandBoard(bandBoardOptionNo);
	}
	
	//myband에서 바로 상세보기..redirection을 이용
	//밴드 홈으로 가기
	//가입된 개별 밴드 들어가기
		@GetMapping("/redirection")
		public String bandGroup(Model model, HttpServletRequest request, @RequestParam String bandNo, String bandBoardDetailNo) {
			HttpSession session = request.getSession();
			OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
			//session.setAttribute("loginUser", ownService.login("hjj"));
			//적합한 이용자인지 조회
			BandMemberDetailVO bandMember = BandMemberDetailVO.builder()
												.bandNo(bandNo)
												.userId(user.getUserId())
												.build();
			bandMember = bandMemberDetailService.getBandMemberDetail(bandMember);
			model.addAttribute("BandMemberDetail", bandMember);
			//밴드+밴드인원수 조회
			Map<String, Object> band = bandService.getBand(bandNo);
			//밴드키워드 자르기
			ArrayList<String> keyword = new ArrayList<String>();
			if(band.get("bandKeyword")!=null) {
				StringTokenizer st = new StringTokenizer((String) band.get("bandKeyword"),"#");
				//처음은 공백이 나와서.. 하나 버리고 감
				st.nextToken();
				while(st.hasMoreTokens()) {
					keyword.add("#"+st.nextToken());
				}
				model.addAttribute("keyword", keyword);
			} else {
		         band.put("bandKeyword","");
			}
			model.addAttribute("band", band);
			//밴드 게시판 조회
			model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
			//밴드의 총 글 수
			model.addAttribute("boardCount", bandBoardDetailService.countBandBoard(bandNo));
			//일정보내기(기본 7일)
			model.addAttribute("calendar", bandBoardDetailService.selectCalendarNum(bandNo, "7"));
			//페이징
			model.addAttribute("bandBoardDetailNo", bandBoardDetailNo);
			return "content/band/bandGroupRedirection";
		}
}
