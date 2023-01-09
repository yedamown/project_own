package co.prjt.own.band.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.ownhome.service.OwnUserVO;


@Controller
@RequestMapping("/band")
public class BandController {
	@Autowired
	BandService bandService;
	//밴드 홈으로 가기
	@RequestMapping("/bandhome")
	public String bandHome(Model model) {
		//원래는 사용자의 각 기호..세션아이디 에 맞춰서 추천 ...........밴드 검색어 넣어야 함
		//1.세션아이디 불러와서 최신글이 있고 가입상태인 밴드목록을 불러옴
		BandMemberDetailVO vo = new BandMemberDetailVO();
		vo.setUserId("hjj");
		model.addAttribute("bandList", bandService.getBandRecentAll(vo));
		return "content/band/bandHome";
	}
	//내 가치 전부보기(페이징 임시로 3개...밴드VO에 개인의 아이디를 리더아이디로 담음..검색시사용)
	//페이징처리(서비스랑 서비스임플까지..후로매퍼검색)
	@RequestMapping("/myBand")
	public String myBand(Model model, OwnUserVO vo, Paging paging) {
		//유저아이디를 가져와 밴드에 담음
		BandVO band = new BandVO();
		band.setBandLeaderid("hjj");
		//밴드 검색...페이지 정보는 ajax로 받아오기
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
}
