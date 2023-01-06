package co.prjt.own.band.mapper;

import java.util.List;
import java.util.Map;

import co.prjt.own.band.service.BandVO;

public interface BandMapper {
	//밴드 생성
	public int insertBand(BandVO vo);
	//밴드 수정
	public int updateBand(BandVO vo);
	//밴드 리스트 전체 + 검색
	public List<Map<String, Object>> getBandAll(BandVO vo);
	//밴드 리스트 전체 + 검색 카운트
	public int count(BandVO vo);
	//밴드 단건조회
	public BandVO getBand(BandVO vo);
	//삭제필요없을듯
}
