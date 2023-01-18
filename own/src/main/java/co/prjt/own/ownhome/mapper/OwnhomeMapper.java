package co.prjt.own.ownhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.common.Paging;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.QuestionVO;
import co.prjt.own.sns.service.SAccountVO;

public interface OwnhomeMapper {
	public int insertUser(OwnUserVO UserVO);
	public OwnUserVO login(String id);
	public int ownUsercount(OwnUserVO vo);
	public List<OwnUserVO> getUserList(OwnUserVO vo);
	public SAccountVO snsLogin(String id);
	public String setPassword(String appNo, String id);
	public int idcheck(String id);	
	public int myupdate();
	public int adQuestionCount();
	//아이디찾기
	public String searchId(String email);
	//이메일보내기
	public String sendMail(String info, String email);
	public int pwcheck(String pw, String newpw);
	//sns 유저 회원가입
	public int updateSnsUser(@Param ("snsNickname") String nickname, @Param ("userId") String id);
	//질문 목록 불러오기
	public List<QuestionVO> questionList();
	//제목 선택시 제목에 맞는 질문 가져오기
	public QuestionVO selectQuest(String qno);
	//질문 답변등록
	public int questionUpdate(QuestionVO vo);
	//내 질문 가져오기
	public List<QuestionVO> myQuestion(QuestionVO vo);
	//내질문 갯수
	public int myquestionCount(QuestionVO vo);
	
}
