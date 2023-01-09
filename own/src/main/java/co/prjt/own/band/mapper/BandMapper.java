package co.prjt.own.band.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;

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
	
	//조인 세션아이디가 들어가있는 밴드 검색
	public List<BandVO> getMemberBandAll(BandMemberDetailVO vo);
	
	//가입한 밴드 중 최신글순대로 나열
	public List<Map<String, Object>> getBandRecentAll(BandMemberDetailVO vo);
	//가입한 밴드 중 최신글순대로 나열 페이지처리
	public List<Map<String, Object>> getMyBandAll(BandVO vo);
	//가입한 밴드 개수
	//여기서 끊어서 vo가 아니라 스트링객체를 받아감
	public int count2(@Param(value = "userId") String userId, @Param(value = "bandName") String bandName);
	
	//밴드세개씩 불러와서 댓글 세개씩 조회
	public List<Map<String, Object>> threeBand(BandVO vo);
}
