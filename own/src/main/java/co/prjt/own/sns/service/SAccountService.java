package co.prjt.own.sns.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SAccountService {
	//SNS가입 계정 전체 조회
	public List<SAccountVO> getSnsUserList(SAccountVO SAccountVO);
	
	//계정 한건 조회
	SAccountVO getSnsUser(String snsNickname);
	
	//SNS 계정가입
	int insertSnsUser(SAccountVO vo);
	
	//프로필 수정
	int updateSnsUser(SAccountVO SAccountVO);
	
	//프로필 사진 유무 체크
	int snsImgCount(String id);
	
	//SNS계정 탈퇴
	int deleteSnsUser(String snsNickname);
	
}
