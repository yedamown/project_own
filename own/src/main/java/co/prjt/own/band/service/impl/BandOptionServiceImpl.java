package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandOptionMapper;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandOptionService;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.mapper.CommonMapper;
import co.prjt.own.ownhome.service.OwnUserVO;

@Service
public class BandOptionServiceImpl implements BandOptionService {
	@Autowired
	BandOptionMapper bandOptionMapper;

	@Override
	public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandManageHome(vo);
	}

	@Override
	public int bandCount(BandMemberDetailVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandCount(vo);
	}

	@Override
	public BandVO bandInfo(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandInfo(vo);
	}

	@Override
	public int bandUpdate(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandUpdate(vo);
	}

	@Override

	public int bandPass(BandVO vo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandPass(vo);
	}

	@Override
	public int bandHuman(String bandNo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandHuman(bandNo);
	}

	@Override
	public int bandDisHuman(String bandNo) {
		// TODO Auto-generated method stub
		return bandOptionMapper.bandDisHuman(bandNo);
	}

	@Override
	public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo) {
		// 페이징 설정
		vo.setBandMemberStatus("BA02"); // 조건 설정
		vo.getPaging().setTotalRecord(bandOptionMapper.bandCount(vo));
		// paging.setPageUnit(1); // 페이징 되는지 확인만 하기 위해 한 페이지에 2개씩만 띄우기
		vo.getPaging().setPageSize(5);
		vo.setFirst(vo.getPaging().getFirst());
		vo.setLast(vo.getPaging().getLast());
		vo.setPaging(vo.getPaging());

		List<BandMemberDetailVO> list = bandOptionMapper.bandOptionGetAllMemberList(vo);
		if(list!=null && list.size()>0) {
			list.get(0).setPaging(vo.getPaging());
		}
		return list;
	}

	@Override
	public List<BandMemberDetailVO> bandOptionGetWaitingMemberList(BandMemberDetailVO vo) {
		// 페이징 설정
		vo.setBandMemberStatus("BA01"); // 조건 설정
		vo.getPaging().setTotalRecord(bandOptionMapper.bandCount(vo));
		// paging.setPageUnit(1); // 페이징 되는지 확인만 하기 위해 한 페이지에 2개씩만 띄우기
		vo.getPaging().setPageSize(5);
		vo.setFirst(vo.getPaging().getFirst());
		vo.setLast(vo.getPaging().getLast());
		vo.setPaging(vo.getPaging());

		// 가치 설정 - 멤버 관리 - 가입대기중 멤버 리스트
		List<BandMemberDetailVO> list = bandOptionMapper.bandOptionGetWaitingMemberList(vo);
		if(list!=null && list.size()>0) {
			list.get(0).setPaging(vo.getPaging());
		}
		return list;
	}

	@Override
	public List<BandMemberDetailVO> bandOptionGetkickedMemberList(BandMemberDetailVO vo) {
		// 페이징 설정
		vo.setBandMemberStatus("BA03"); // 조건 설정
		vo.getPaging().setTotalRecord(bandOptionMapper.bandCount(vo));
		// paging.setPageUnit(1); // 페이징 되는지 확인만 하기 위해 한 페이지에 2개씩만 띄우기
		vo.getPaging().setPageSize(5);
		vo.setFirst(vo.getPaging().getFirst());
		vo.setLast(vo.getPaging().getLast());
		vo.setPaging(vo.getPaging());

		List<BandMemberDetailVO> list = bandOptionMapper.bandOptionGetkickedMemberList(vo);
		if(list!=null && list.size()>0) {
			list.get(0).setPaging(vo.getPaging());
		}
		return list;
	}

	@Override
	public int updateBandMemberStatus(BandMemberDetailVO vo) {
		// 밴드 멤버 상태 업데이트
		return bandOptionMapper.updateBandMemberStatus(vo);
	}

	@Override
	public List<BandMemberDetailVO> selectBandMemberInfo(BandMemberDetailVO vo) {
		// 모달에 띄울 밴드 멤버 상세 정보
		return bandOptionMapper.selectBandMemberInfo(vo);
	}

}
