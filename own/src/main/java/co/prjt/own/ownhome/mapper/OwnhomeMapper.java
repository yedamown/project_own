package co.prjt.own.ownhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.sns.service.SAccountVO;

public interface OwnhomeMapper {
	public int insertUser(OwnUserVO UserVO);
	public OwnUserVO login(String id);
	public List<OwnUserVO> getUserList(OwnUserVO vo);
	public SAccountVO snsLogin(String id);

	public int idcheck(String id);	
	
	
	//sns 유저 회원가입
	public int updateSnsUser(@Param ("snsNickname") String nickname, @Param ("userId") String id);
	
}
