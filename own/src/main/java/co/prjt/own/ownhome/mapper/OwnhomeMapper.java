package co.prjt.own.ownhome.mapper;

import java.util.List;

import co.prjt.own.ownhome.service.OwnUserVO;

public interface OwnhomeMapper {
	public int insertUser(OwnUserVO UserVO);
	public OwnUserVO login(String id);
	public List<OwnUserVO> getUserList(OwnUserVO vo);
	public String snsLogin(String id);

	public int idcheck(String id);	
	//sns 유저 회원가입
	public int updateSnsUser(String id);
	
}
