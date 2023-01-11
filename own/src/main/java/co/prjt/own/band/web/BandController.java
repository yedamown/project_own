package co.prjt.own.band.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.ownhome.service.OwnUserVO;


@Controller
@RequestMapping("/own/band")
public class BandController {
	@Autowired
	BandService bandService;
	@Autowired
	BandMemberDefaultService bandMemberDefaultService;
	//밴드 홈으로 가기
	@RequestMapping("")
	public String bandHome(Model model, HttpServletRequest request) {
		//원래는 사용자의 각 기호..세션아이디 에 맞춰서 추천 ...........밴드 검색어 넣어야 함
		//1.세션아이디 불러와서 최신글이 있고 가입상태인 밴드목록을 불러옴..위치설정
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
	      
	    System.out.println(user);
	    //세션널..임시
	  		if(user==null) {
	  			return "content/own/ownlogin";
	  		}
		BandMemberDetailVO vo = new BandMemberDetailVO();
		//vo.setUserId("hjj");
		vo.setUserId(user.userId);
		//유저디폴트정보 싣기
		model.addAttribute("user", bandMemberDefaultService.getBandMemberDefault(user.userId));
		//가입한..최신글올라온 밴드 불러오기
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
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		if(user==null) {
  			return "content/own/ownlogin";
  		}
	    System.out.println(user.userId);
	  //세션널..임시
  		
		band.setBandLeaderid(user.userId);
		//밴드 검색...페이지 정보는 ajax로 받아오기
		model.addAttribute("bandName", band.getBandName());
		model.addAttribute("myBand", bandService.getMyBandAll(band, paging));
		return "content/band/myBand";
		
	}
	//RestController..댓글세개씩보내기
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
		//페이지이동용으로도 같이쓰게..여기선 paging안쓰지만 
		Paging paging = new Paging();
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
}