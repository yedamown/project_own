package co.prjt.own.common.web;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.chall.service.ValidationVO;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.ExersubVO;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeService;
import co.prjt.own.common.service.OwnLikeVO;
import co.prjt.own.common.service.ReportVO;
import co.prjt.own.ownhome.service.OwnhomeService;


@Controller
public class CommonController {
	
	@Autowired
	CommonService commonService;

	@Autowired
	OwnhomeService ownhomeService;
	
	@Autowired OwnLikeService likeService;
	
	@Value("${spring.servlet.multipart.location}")
	String filePath;
	
	// 운동종류 가져오기
	@GetMapping("/common/exersub")
	@ResponseBody//데이터를 반환할때는 무조건 리스폰스바디 넣기
	public List<ExersubVO> getListexersub(ExersubVO vo){
		return commonService.getListExersub();
	}
	
	//신고 폼 이동
	@GetMapping("/own/admin/report")
	public String reportForm(Model model,List<ReportVO> vo) {
		//model.addAttribute("lRecord", exerMapper.LatestExerRecord(user.getUserId()));
		vo = commonService.reportAlllist();
		return "content/own/report";
	}	
	//이거 서비스에 넣기	
	@GetMapping("/download")
	public ResponseEntity<Resource> download(@ModelAttribute FileDto dto) throws IOException{
		Path path = Paths.get(filePath + "/" + dto.getUuid() + "_" + dto.getFileName());
		
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename(dto.getFileName(),
						StandardCharsets.UTF_8).build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path)); 
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	//좋아요 확인 후 등록 or 삭제 -> 아작스 처리로 보내주깅
	@PostMapping("/own/likeAjax")
	@ResponseBody
	public String LikeAdd(@RequestBody OwnLikeVO vo) {
		//아작스로 넘길때 category도 함께 넘기세용
		//등록 시 add, 북마크 삭제시 del, 그 외의 경우 error 반환
		System.out.println(vo);
		String result = likeService.checkLike(vo);
		return result;
	}
	//각 페이지에서 리턴값으로 스크립트에서 처리하면 될 것 같습니다.. 제가틀렸다면 알려주십숑
}

