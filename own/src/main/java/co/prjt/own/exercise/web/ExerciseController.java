package co.prjt.own.exercise.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.exercise.mapper.ExerRecordMapper;
import co.prjt.own.exercise.service.ExerRecordVO;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
@RequestMapping("/own")
public class ExerciseController {
	@Autowired
	ExerRecordMapper exerMapper;

	// 오운완(나의운동기록하기) 페이지 이동
	@RequestMapping(value = "/ownRecordForm", method = RequestMethod.GET)
	public String ownRecordForm() {
		return "content/own/ownRecordForm";
	}

	// 오운완(나의운동기록) 등록
	@PostMapping("/exerciseRecord")
	@ResponseBody
	public ExerRecordVO exerciseRecord(ExerRecordVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		String id = user.getUserId();
		vo.setUserId(id);
		System.out.println(vo);
		exerMapper.insertExerRecord(vo);
		return vo;
	}

	// 오운완(나의운동기록보기) 페이지 이동
	@RequestMapping(value = "/ownRecordList", method = RequestMethod.GET)
	public String ownRecordList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		// 세션에 담긴 아이디로 해당 회원의 가장 최신날짜 운동기록 가져오기
		model.addAttribute("lRecord", exerMapper.LatestExerRecord(user.getUserId()));
		return "content/own/ownRecordList";
	}

	// 회원의 가장 최신날짜 기록의 운동 개수 가져오기
	@GetMapping("/dayChart")
	@ResponseBody
	public List<ExerRecordVO> dayExerChart(@RequestParam String userId) {
		return exerMapper.dayRecordCounting(userId);
	}

	// 회원의 가장 최신날짜 기록의 운동 개수 가져오기
	@GetMapping("/getWeight")
	@ResponseBody
	public List<ExerRecordVO> WeightChart(@RequestParam String userId) {
		return exerMapper.getWeight(userId);
	}

	// 기간설정해서 회원의 운동 기록 가져오기
	@PostMapping("/selectRecord")
	@ResponseBody
	public List<ExerRecordVO> selectRecord(@RequestParam String userId, @RequestParam Date startDate, @RequestParam Date endDate) {
		System.out.println("=======유저아이디1"+userId);
		return exerMapper.selectRecord(userId, startDate, endDate);
	}
	
	// 기간설정해서 회원의 몸무게 가져오기
	@PostMapping("/selectWeight")
	@ResponseBody
	public List<ExerRecordVO> selectWeight(@RequestParam("userId") String userId, @RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) {
		System.out.println("=======유저아이디2"+userId);
		return exerMapper.selectWeight(userId, startDate, endDate);
	}

	// 기간설정해서 회원의 운동 카운팅 가져오기
	@PostMapping("/selectCounting")
	@ResponseBody
	public List<ExerRecordVO> selectCounting(@RequestParam("userId") String userId, @RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) {
		System.out.println("=======유저아이디3"+userId);
		return exerMapper.selectCounting(userId, startDate, endDate);
	}
}
