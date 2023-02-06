package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		paging.setTotalRecord(mapper.countMyAMT(vo));
		paging.setPageUnit(10); //내 도전이라 6개씩 보여줄 것
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

}
