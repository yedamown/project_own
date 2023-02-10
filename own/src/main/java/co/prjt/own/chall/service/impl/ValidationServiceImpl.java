package co.prjt.own.chall.service.impl;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.mapper.ValidationMapper;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.chall.service.ValidationService;
import co.prjt.own.chall.service.ValidationVO;
import co.prjt.own.common.Paging;

@Component
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	ValidationMapper validation;
	
	@Autowired
	ChallengeMapper challenges;

	@Override
	public int insertVld(ValidationVO vo) {
		return validation.insertVld(vo);
	}

	@Override
	public int updateVld(ValidationVO vo) {
		return validation.updateVld(vo);
	}

	@Override
	public ValidationVO getVld(ValidationVO vo) {
		return validation.getVld(vo);
	}

	// 페이징곁들인 매퍼..인데 이 서비스보다는 매퍼를 사용.
//	@Override
//	public List<ValidationVO> getChallVld(ValidationVO vo) {
//		return mapper.getChallVld(vo);
//	}

	@Override
	public List<ValidationVO> getMyVld(ValidationVO vo) {
		return validation.getMyVld(vo);
	}

	@Override
	public List<ValidationVO> getMyChallVld(ValidationVO vo) {
		return validation.getMyChallVld(vo);
	}

	// 주에 인증 횟수 가져오기
	@Override
	public int countWeekVld(ValidationVO vo) {
		// 시작날로부터 며칠째인지.
		int whatWeek = validation.whatWeekToday(vo);
		System.out.println("=== 가져온 vo" + vo);
		if (whatWeek == 0) {
			// 만약에 당일이라 0이 나와도 첫주니까 1로 계산.
			whatWeek = 1;
		}
		int beforeWeek = whatWeek - 1;
		System.out.println("============= 전 주" + beforeWeek);
		System.out.println("============= 이번 주" + whatWeek);
		vo.setBeforeWeek(beforeWeek);
		vo.setNowWeek(whatWeek);
		System.out.println("=== 설정해서 이제 날짜검색 " + vo);
		// 이번주 인증 수 계산.
		int rs = validation.countWeekVld(vo);

		return rs;
	}

	// 총 인증횟수
	@Override
	public int countVld(ValidationVO vo) {
		return validation.countVld(vo);
	}

	// 오늘 인증 횟수
	@Override
	public int todayVld(ValidationVO vo) {
		return validation.todayVld(vo);
	}

	// 도전멤버 인증 평균
	@Override
	public int memVldAvg(ValidationVO vo) {
		return validation.memVldAvg(vo);
	}

	//
	@Override
	public List<ValidationVO> getVldPageList(ValidationVO vo, Paging paging) {
		System.out.println("---------------------인증" + vo);
		paging.setTotalRecord(validation.countVld(vo));
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPaging(paging);
		if(vo.getUserId() == null) {
			List<ValidationVO> list = validation.getChallVld(vo);			
		    if(list!=null && list.size()>0) {
		    	list.get(0).setPaging(paging);
		    }		
			return list;
		} else {
			List<ValidationVO> list = validation.getMyVld(vo);	
		    if(list!=null && list.size()>0) {
		    	list.get(0).setPaging(paging);
		    }
			return list;
		}
	}

	/// 이번주 오늘인증 체크해서..인증가능한지?
	@Override
	public int vldWeekCheck(ValidationVO vo) {
		// 시작날로부터 며칠째인지.
		String challNo =  vo.getChallNo();
		int whatWeek = validation.whatWeekToday(vo);
		System.out.println("=== 가져온 vo" + vo);
		if (whatWeek == 0) {
			// 만약에 당일이라 0이 나와도 첫주니까 1로 계산.
			whatWeek = 1;
		}
		int beforeWeek = whatWeek - 1;
		vo.setBeforeWeek(beforeWeek);
		vo.setNowWeek(whatWeek);
		// 이번주 인증 수 계산 (인증 vo에 유저아이디와 도전번호 검색)
		int rs = validation.countWeekVld(vo);
		//도전의 한주 인증 수
		ChallengeVO cvo1 = new ChallengeVO();
		cvo1.setChallNo(challNo);
		ChallengeVO cvo2 = new ChallengeVO();
		cvo2 = challenges.getChall(cvo1);
		int cVld = cvo2.getChallFreq();
		//이번주가 한주인증수 미만. 작을경우 가능 = 1 리턴.
		if( rs < cVld) {
			return 1;
		} else {
			return 0;			
		}
	}

	//페이징 정보없는 매퍼적용하는 서비스..
	@Override
	public List<ValidationVO> getVldList(ValidationVO vo) {
		return validation.getVldList(vo);
	}

	//인증삭제
	@Override
	public int deleteVld(ValidationVO vo) {
		return validation.deleteVld(vo);
	}

}
