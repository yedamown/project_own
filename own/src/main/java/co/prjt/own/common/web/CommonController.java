package co.prjt.own.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.common.mapper.CommonMapper;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.ExersubVO;


@Controller
public class CommonController {
		
	@Autowired
	CommonService commonService;
	
	@GetMapping("/common/exersub")
	@ResponseBody//데이터를 반환할때는 무조건 리스폰스바디 넣기
	public List<ExersubVO> getListexersub(ExersubVO vo){
		return commonService.getListExersub();
	}
}
