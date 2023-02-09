package co.prjt.own.chall.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.prjt.own.chall.mapper.CAmountMapper;
import co.prjt.own.chall.mapper.CMemberMapper;
import co.prjt.own.chall.service.CAmountService;
import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.chall.service.CMemberVO;
import co.prjt.own.common.Paging;

@Component
public class CAmountServiceImpl implements CAmountService{

	@Autowired CAmountMapper mapper;
	
	//현재 예치금 같이 결제하기 위해.
	@Autowired CMemberMapper member;
	
	@Override
	public int insertAmount(CAmountVO vo, CMemberVO mem) {
		System.out.println(vo);
		String type = vo.getAmtType();
		System.out.println("============유형================" + type);
		if(type.equals("출금") || type.equals("도전참가")) {
			mem.setUserId(vo.getUserId());
			mem.setPrice(vo.getPrice());
			member.minusAmt(mem);
		} else {
			mem.setUserId(vo.getUserId());
			mem.setPrice(vo.getPrice());
			member.plusAmt(mem);
		}
		return mapper.insertAmount(vo);
	}

	//단건조회
	@Override
	public CAmountVO getAmt(CAmountVO vo) {
		return mapper.getAmt(vo);
	}
	
	
	@Override
	public List<CAmountVO> getAmountListAll() {
		return mapper.getAmountListAll();
	}

	@Override
	public List<CAmountVO> getAmountList(CAmountVO vo) {
		return mapper.getAmountList(vo);
	}
	
	//페이징정보 넣기
	@Override
	public List<CAmountVO> getAmtListPage(CAmountVO vo, Paging paging) {
		if(vo.getAmtType() !=null && vo.getAmtType().equals("전체보기")) {
			vo.setAmtType(null);
		} 
		System.out.println("보보보"+ vo);
		paging.setTotalRecord(mapper.countMyAMT(vo));
		paging.setPageUnit(5); //내 도전이라 6개씩 보여줄 것
		paging.setPageSize(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPaging(paging);
		System.out.println("===================예치금페이징" + vo);
		List<CAmountVO> list = mapper.getAmountList(vo);
		if( list!=null && list.size()>0) {
			list.get(0).setPaging(paging);
		}
		return list;
	}

	@Override
	public ResponseEntity<JsonNode> refundMoney(CAmountVO vo) {
		 String url = "https://developers.nonghyup.com/ReceivedTransferOtherBank.nh";
	      //출금이라는 유형 고정
		  String type = vo.getAmtType();
	      //출금은행정보 + 계좌번호
	      String method = vo.getAmtMethod();
	      //출금금액
	      int price = vo.getPrice();
	      
	      //오늘날짜 구하는 메소드
	      LocalDate now = LocalDate.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	      String today = now.format(formatter);
	      
	      //지금시간 구하는..
	      LocalTime nowTime = LocalTime.now();
	      DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HHmmss");
	      String formatedNow = nowTime.format(tformatter);
	      
	      ///오늘날짜 + 지금시간
	      String IsTuno = today+formatedNow;
	      Map<String, Object> header = new HashMap<String, Object>();
	      Map<String, Object> param = new HashMap<String, Object>();
	      
	      //api명 농협계좌로 주기
	      header.put("ApiNm","ReceivedTransferOtherBank");
	      //전송일자
	      header.put("Tsymd",today);
	      //전송시각
	      header.put("Trtm",formatedNow);
	      //기관거래고유번호 - 내 아이디에있는
	      header.put("Iscd","001723");
	      //핀테크 앱 일련번호 - 고정
	      header.put("FintechApsno","001");
	      //서비스코드 - 고정
	      header.put("ApiSvcCd","DrawingTransferA");
	      //위에서 정한
	      header.put("IsTuno",IsTuno);
	      //내 인증키
	      header.put("AccessToken","384a547964275fb4f3aede03b47693bb77c051117491b60eea2bb011ba362a31");
	      
		  //우리테이블에 입력할 정보...vo로 받아서 넣고..
	      CMemberVO mem = new CMemberVO();
	      mem.setUserId(vo.getUserId());
	      mem.setPrice(vo.getPrice());
	      //돈빼가니깐 ㅋ
	      member.minusAmt(mem);
	      vo.setAmtType("출금");
	      //method에 은행이랑 계좌번호 넣어서 보내야할듯?
	      mapper.insertAmount(vo);
//		  param.put("Header",header);
//	      param.put("Bncd", bankName);
//	      param.put("Acno", bankAccount);
//	      param.put("Tram", moPrice);
//	      param.put("DractOtlt", "이벤티");
	      
	      //header설정
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      
	      ObjectMapper obMapper = new ObjectMapper();
	      
	      HttpEntity<String> request = null;
	      try {
	         request = new HttpEntity<String>(obMapper.writeValueAsString(param), headers);
	      } catch (JsonProcessingException e) {
	         e.printStackTrace();
	      }
	      //uri 생성
	      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);                     
	      
	      //restTemplate
	      RestTemplate restTpl = new RestTemplate();
	      
	      //result JsonNode 생성
	      JsonNode responseEntity = restTpl.postForObject(builder.toUriString(), request, JsonNode.class);
	      return ResponseEntity.ok(responseEntity);
	}

}
