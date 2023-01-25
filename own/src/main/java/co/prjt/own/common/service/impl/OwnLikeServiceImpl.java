package co.prjt.own.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.prjt.own.common.mapper.OwnLikeMapper;
import co.prjt.own.common.service.OwnLikeService;
import co.prjt.own.common.service.OwnLikeVO;

@Component
public class OwnLikeServiceImpl implements OwnLikeService{
	
	@Autowired OwnLikeMapper mapper;
	
	@Override
	public int countLike(OwnLikeVO vo) {
		return mapper.countLike(vo);
	}
	
	@Override
	public OwnLikeVO getLike(OwnLikeVO vo) {
		return mapper.getLike(vo);
	}

	@Override
	public List<OwnLikeVO> getAllLike(OwnLikeVO vo) {
		return mapper.getAllLike(vo);
	}

	@Override
	public int addLike(OwnLikeVO vo) {
		return mapper.addLike(vo);
	}

	@Override
	public int delLike(OwnLikeVO vo) {
		return mapper.delLike(vo);
	}

	
	///매퍼들 이용해서 북마크 확인하고 처리하는 서비스..
	@Override
	public String checkLike(OwnLikeVO vo) {
		System.out.println(vo);
		int rs =  mapper.countLike(vo); //카운트 갯수로.. 여부판단하기로함!
		//등록 시 add, 북마크 삭제시 del, 그 외의 경우 error 반환
		if(rs == 0) {
			mapper.addLike(vo);
			return "add";
		// else 좋아요 있으면 삭제처리 후 'del' 넘겨주기
		} else if(rs == 1) {
			mapper.delLike(vo);
			return "del";
		} else {
			return "error";
		}
	}
	
	
}
