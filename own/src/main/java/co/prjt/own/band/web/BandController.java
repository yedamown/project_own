package co.prjt.own.band.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.prjt.own.band.service.BandBoardDetailSearchVO;
import co.prjt.own.band.service.BandBoardDetailService;
import co.prjt.own.band.service.BandBoardOptionService;
import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;


@Controller
@RequestMapping("/own/band")
public class BandController {
	@Autowired BandService bandService;
	@Autowired BandMemberDefaultService bandMemberDefaultService;
	@Autowired CommonService common;
	@Autowired BandBoardOptionService bandBoardOptionService;
	@Autowired OwnhomeService ownService;
	@Autowired BandBoardDetailService bandBoardDetailService;
	//밴드 홈으로 가기
	@RequestMapping("")
	public String bandHome(Model model, HttpServletRequest request) {
		//원래는 사용자의 각 기호..세션아이디 에 맞춰서 추천 ...........밴드 검색어 넣어야 함
		//1.세션아이디 불러와서 최신글이 있고 가입상태인 밴드목록을 불러옴..위치설정
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", ownService.login("hjj")); 
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		/*
		 * System.out.println(user); //세션널..임시 if(user==null) { return
		 * "content/own/ownlogin"; }
		 */	  		
		BandMemberDetailVO vo = new BandMemberDetailVO();
		vo.setUserId(user.userId);
		
		//유저디폴트정보 싣기  DT : BandMemberDefaultVO
		model.addAttribute("user", bandMemberDefaultService.getBandMemberDefault(user.userId));
		//가입한..최신글올라온 밴드 불러오기 DT : List<Map<String, Object>> Object:bandVO+mapper +impl에서 이미지도 넣음
		model.addAttribute("bandList", bandService.getBandRecentAll(vo));
		//운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		//인기글 세개 싣기
		
		return "content/band/bandHome";
	}
	//내 가치 전부보기(페이징 임시로 3개...밴드VO에 개인의 아이디를 리더아이디로 담음..검색시사용)
	//페이징처리(서비스랑 서비스임플까지..후로매퍼검색)
	@RequestMapping("/myBand")
	public String myBand(Model model, OwnUserVO vo, Paging paging, BandVO band, HttpServletRequest request) {
		//유저아이디를 가져와 밴드에 담음
		System.out.println(paging.toString());
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		if(user==null) {
  			return "content/own/ownlogin";
  		}
		band.setBandLeaderid(user.userId);
		//페이지 정보는 ajax로 받아오기 참고)내 밴드보기에서 검색용
		model.addAttribute("bandName", band.getBandName());
		
		model.addAttribute("myBand", bandService.getMyBandAll(band, paging));
		return "content/band/myBand";
		
	}
	//RestController..최신글세개씩보내기
	@GetMapping("/myBand/{threeBand}")
	@ResponseBody
	public List<Map<String, Object>> threeBand(@PathVariable String threeBand){
		//한문장으로 된 번호를 보냄..ex)BDU_17BDU_15BDU_14 
		return bandService.threeBand(threeBand);
	}
	//추천밴드 가져오기(관심운동과 지역에 맞춰서)
	@GetMapping("/recom/{userId}")
	@ResponseBody
	public List<BandVO> recomBand(@PathVariable String userId){
		//유저 아이디지만 스트링으로 여러가지 값이 담김
		return bandService.recomBand(userId);
	}
	//밴드추천보기..recomBand()와 매퍼를 같이 씀. 페이징용
	@RequestMapping("/bandRec")
	public String bandRec(Model model, Paging paging, HttpServletRequest request, BandVO band) {
		//유저아이디 가져오기
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		//운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		
		band.setBandLeaderid(user.getUserId());
		
		model.addAttribute("bandList", bandService.recomBandPage(band, paging));
		return "content/band/bandSearch";
	}
	//홈에서 밴드검색
	@RequestMapping("/bandSearch")
	public String bandSearch(Model model, Paging paging, BandVO band) {
		//운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		model.addAttribute("bandList", bandService.getBandAll(band, paging));
		return "content/band/bandSearch";
	}
	//밴드생성
	@RequestMapping("/bandCreate")
	public String bandCreate(Model model, HttpServletRequest request) {
		//유저아이디 가져오기
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		model.addAttribute("userId", user.getUserId());
		//운동종류+관심지역 셀렉트박스
		model.addAttribute("location", bandService.allLocation());
		model.addAttribute("exercise", bandService.allExcersie());
		//샘플이미지 여덟장 실어보내기
		model.addAttribute("createImage", common.selectImgAll("BAND_CREATE"));
		return "content/band/bandCreate";
	}
	//밴드생성 mutimediavo는 대표이미지를 샘플로 했을 때 파일명을 받아오기 위함//+밴드리더의 멤버디테일생성
	@PostMapping("/bandCreate")
	public String bandCreateComplete(@RequestParam MultipartFile gateImage[], BandVO band, RedirectAttributes rttr, MultimediaVO sampleImgInput) {
		System.out.println(band.toString());
		System.out.println(sampleImgInput.toString());
		//밴드생성
		band = bandService.insertBand(band);
		//밴드생성 성공
		if(band.getBandNo()!=null) { //bandNo 숫자로 들어옴
			//밴드대표이미지 추가하기 (MultipartFile[], 밴드번호숫자부분, 밴드번호앞자리(BDU_), "Band")
			//만약 사용자가 대표이미지를 넣었다면
			if(sampleImgInput.getMediaServerFile().equals("sample")) {//여기값이 샘플이란 건 실제 들어온 값이 샘플이라는 거..
				String res = common.upload(gateImage, band.getBandNo(), "BDU_", "Band");
				System.out.println(res);
			} else {
			//만약 사용자가 대표이미지를 샘플이미지로 했다면
				sampleImgInput.setMediaRealFile("band_실제 이미지 없음");
				sampleImgInput.setMediaFilePath("c:/test");
				sampleImgInput.setIno("BDU_");
				sampleImgInput.setIdentifyId(band.getBandNo());
				sampleImgInput.setMediaCategory("Band");
				sampleImgInput.setMediaTurn("band_sample");
				bandService.bandSampleimg(sampleImgInput);
			}
			return "redirect:content/band/bandCreateComplete";
		}
		return "redirect:content/band/bandCreateFail";
	}
	
	//가입된 개별 밴드 들어가기
	@GetMapping("/bandGroup")
	public String bandGroup(Model model, HttpServletRequest request, @RequestParam String bandNo) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		session.setAttribute("loginUser", ownService.login("hjj"));
		//밴드+밴드인원수 조회
		Map<String, Object> band = bandService.getBand(bandNo, user.getUserId());
		//밴드키워드 자르기
		ArrayList<String> keyword = new ArrayList<String>();
		if(band.get("bandKeyword")!=null) {
			StringTokenizer st = new StringTokenizer((String) band.get("bandKeyword"),"#");
			//처음은 공백이 나와서.. 하나 버리고 감
			st.nextToken();
			while(st.hasMoreTokens()) {
				keyword.add("#"+st.nextToken());
			}
		}
		model.addAttribute("band", band);
		model.addAttribute("keyword", keyword);
		//밴드 게시판 조회
		model.addAttribute("boardList", bandBoardOptionService.getBandBoardList(bandNo));
		//밴드의 총 글 수
		model.addAttribute("boardCount", bandBoardDetailService.countBandBoard(bandNo));
		//임시 최신글5개랑 페이지번호담아 보내기
		
		return "content/band/bandGroup";
	}
	//RestController..밴드 상세...글번호 5개 주면 최신글 5개씩 내보내기
	@GetMapping("/bandGroup/fiveBoard")
	@ResponseBody
	public List<BandBoardDetailSearchVO> fiveBoard(BandBoardDetailSearchVO vo, HttpServletRequest request){
		//좋아요 용으로 받아오는 세션
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		return bandBoardDetailService.getFiveBoard(vo ,user.userId);
	}
	
	//밴드내 모든 게시판
	@GetMapping("/bandGroup/bandBoardList")
	public String bandMainGroup(Model model, HttpServletRequest request, @RequestParam String bandNo, Paging paging) {
		BandBoardDetailSearchVO vo = new BandBoardDetailSearchVO();
		vo.setBandNo(bandNo);
		//System.out.println(vo.toString());
		//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
		model.addAttribute("boardList", bandBoardDetailService.getBandBoard(vo, paging));
		return "content/band/bandBoardList";
	}
	//Ajax//밴드내 모든 게시판//위와 세트
	@ResponseBody
	@GetMapping("/bandGroup/bandBoardListAjax")
	public List<BandBoardDetailSearchVO> bandMainGroupAjax(Model model, HttpServletRequest request, BandBoardDetailSearchVO vo, Paging paging) {
		System.out.println(vo.toString());
		System.out.println(paging.toString());
		//밴드번호를 가져오면 모든 글과...페이징처리해서보냄
		return bandBoardDetailService.getBandBoard(vo, paging);
	}
}