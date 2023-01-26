package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;

public interface BandOptionMapper {
	List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo);
}
