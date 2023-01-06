package co.prjt.own.band.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.band.mapper.BandMapper;


@Controller
public class BandController {
	@Autowired
	BandMapper bandMapper;
	//밴드 홈으로 가기
	@RequestMapping(value = "/band/bandhome", method = RequestMethod.GET)
	public String bandHome(Model model) {
		//원래는 사용자의 각 기호에 맞춰서 추천 ...........밴드 검색어 넣어야 함
		//model.addAttribute("bandList", bandMapper.getBandAll(null));
		return "content/band/bandhome";
	}
}
