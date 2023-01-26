package co.prjt.own.band.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.prjt.own.band.service.BandOptionService;

@Controller
public class BandOptionController {

	@Autowired
	BandOptionService bandOptionService;
	
	@GetMapping("/own/band/bandGroup/bandOptionMain")
	public String bandOptionMain(Model model) {
		
		
		return "/content/band2/bandOption";
	}
	
	
	
}
