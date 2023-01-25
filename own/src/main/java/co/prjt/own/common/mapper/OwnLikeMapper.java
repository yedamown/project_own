package co.prjt.own.common.mapper;

import java.util.List;

import co.prjt.own.common.service.OwnLikeVO;

public interface OwnLikeMapper {
	
		//좋아요  - 단건조회
		OwnLikeVO getLike(OwnLikeVO vo);

		//좋아요 리스트 출력
		List<OwnLikeVO> getAllLike(OwnLikeVO vo);
		
		//좋아요 등록
		int addLike(OwnLikeVO vo);
		
		//좋아요 삭제
		int delLike(OwnLikeVO vo);
		
		//좋아요 카운트 - 아이디, 각식별번호코드로 조회
		int countLike(OwnLikeVO vo);
}
