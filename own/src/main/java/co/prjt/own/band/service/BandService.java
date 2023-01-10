package co.prjt.own.band.service;

import java.util.List;
import java.util.Map;

import co.prjt.own.common.Paging;

public interface BandService {
		//밴드 생성
		public int insertBand(BandVO vo);
		//밴드 수정
		public int updateBand(BandVO vo);
		//밴드 리스트 전체 + 검색
		//밴드 관심지역+좋아하는 운동있으면 그걸로 검색하기... 없으면 그냥
		public List<Map<String, Object>> getBandAll(BandVO vo, Paging paging);
		//밴드 리스트 전체 + 검색 카운트..위와세트
		public int count(BandVO vo);
		
		//밴드 단건조회
		public BandVO getBand(BandVO vo);
		//삭제필요없을듯

		//조인 세션아이디가 들어가있는 밴드 검색
		public List<BandVO> getMemberBandAll(BandMemberDetailVO vo);
		//가입한 밴드 중 최신글순대로 나열
		//밴드회원수 + 밴드..
		public List<Map<String, Object>> getBandRecentAll(BandMemberDetailVO vo);
		//가입한 밴드 중 최신글순대로 나열 페이지처리
		//리더아이디로 가입자의 명을 넣음...(검색용도...)
		//검색시에도 사용하기위에 
		public List<Map<String, Object>> getMyBandAll(BandVO vo, Paging paging);
		//가입한 밴드 개수
		public int count2(BandMemberDetailVO vo, BandVO vo2);
		
		//밴드세개씩 불러와서 댓글 세개씩 조회
		public List<Map<String, Object>> threeBand(String threeBand);
		
		//추천밴드를 위한 지역셀렉트박스
		public List<Map<String, String>> allLocation();
		//추천밴드를 위한 관심종목셀렉트박스
		public List<Map<String, String>> allExcersie();
		
		//추천밴드
		public List<BandVO> recomBand(String userId);
}
