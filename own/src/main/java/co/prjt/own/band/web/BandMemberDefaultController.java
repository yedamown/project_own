package co.prjt.own.band.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDefaultService;
import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandVO;

@Controller
@RequestMapping("/own/band")
public class BandMemberDefaultController {
	@Autowired
	BandMemberDefaultService bandMemberDefaultService;

	@PutMapping("/up")
	@ResponseBody
	public BandMemberDefaultVO updateMemberDf(@RequestBody BandMemberDefaultVO vo) {
		bandMemberDefaultService.updateMemberDf(vo);
		return vo;
	}
	//================================= 밴드 옵션 임시 컨트롤러 =================================
	//밴드 수정페이지로 이동 
   @GetMapping("/bandGroup/bandOption")
   public String bandOption(Model model, HttpServletRequest request, BandVO vo) {
      //임시텍스트
      model.addAttribute("imsi", "임시텍스트 밴드설정");
      return "content/band2/bandOption";
   }
   
 //밴드 수정페이지로 이동 
   @GetMapping("/bandGroup/bandOption2")
   public String bandOption2(Model model, HttpServletRequest request, BandVO vo) {
      //임시텍스트
      model.addAttribute("imsi", "임시텍스트 밴드설정");
      return "content/band2/bandOption2";
   }

}
