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
		//밴드 리스트 전체 + 검색 카운트
		public int count(BandVO vo);
		//밴드 단건조회
		public BandVO getBand(BandVO vo);
		//삭제필요없을듯
}
