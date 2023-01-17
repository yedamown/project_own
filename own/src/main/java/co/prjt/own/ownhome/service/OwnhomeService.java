package co.prjt.own.ownhome.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.sns.service.SAccountVO;

public interface OwnhomeService {
	//모든회원정보
	public List<OwnUserVO> getUserList(OwnUserVO vo);
	//회원가입
	public int insertUser(OwnUserVO UserVO);
	//아이디 중복체크
	public int idcheck(String id);	
	//로그인
	public OwnUserVO login(String id);
	//아이디찾기
	public String searchId(String email);
	//이메일보내기
	public String sendMail(String info, String email);
	//내정보 수정
	public int myupdate();
	//비밀번호 체크
	public int pwcheck(String pw, String newpw);
	//비밀번호 재설정
	public String setPassword(String appNo, String id);
	
	public SAccountVO snsLogin(String id);
	public int updateSnsUser(@Param ("snsNickname") String nickname, @Param ("userId") String id);
	//sns 유저 회원가입
	
	//=============관리자============
	//질문목록 불러오기
	public List<QuestionVO> questionList();
	//제목 선택시 제목에 맞는 질문 가져오기
	public QuestionVO selectQuest(String qno);
	//질문 답변등록
	public int questionUpdate(QuestionVO vo);
	//내 질문 가져오기
	public List<QuestionVO> myQuestion(String id);
}
