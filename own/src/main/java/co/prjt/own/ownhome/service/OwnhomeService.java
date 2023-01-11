package co.prjt.own.ownhome.service;

import java.util.List;

public interface OwnhomeService {
	//모든회원정보
	public List<OwnUserVO> getUserList(OwnUserVO vo);
	//회원가입
	public int insertUser(OwnUserVO UserVO);
	//아이디 중복체크
	public int idcheck(String id);	
	//로그인
	public OwnUserVO login(String id);
	
	
	
	public String snsLogin(String id);
	
	//sns 유저 회원가입
	public int updateSnsUser(String id);
}
