package co.prjt.own.sns.mapper;

import java.util.List;

import co.prjt.own.sns.service.SBoardVO;

public interface SBoardMapper {
	
     //SNS 게시글 전체 조회
	 public List<SBoardVO> getSnsBoardList(SBoardVO SBaordVO);
	 
	 //sns 게시글 한건 조회
	 SBoardVO getSnsBoard(String snsBoardNo);
	 
	 //sns 게시글 입력
	 int insertSnsBoard(SBoardVO SBoardVO);
	 
	 //sns 게시글 수정
	 int updateSnsBoard(SBoardVO SBoardVO);
	 
	 //sns 게시글 삭제
	 int deleteSnsBoard(String snsBoardNo);
	 
}
