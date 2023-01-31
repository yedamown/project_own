package co.prjt.own.ownhome.service.impl;

import java.security.SecureRandom;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.common.Paging;
import co.prjt.own.common.service.ReportVO;
import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.CustomUser;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.ownhome.service.QuestionVO;
import co.prjt.own.sns.service.SAccountVO;

@Service
public class OwnhomeServiceImpl implements OwnhomeService,UserDetailsService {

	@Autowired
	OwnhomeMapper ownhomeMapper;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	@Override
	public int insertUser(OwnUserVO UserVO) {
		return ownhomeMapper.insertUser(UserVO);
	}

	@Override
	public OwnUserVO login(String id) {
		return ownhomeMapper.login(id);
	}

	@Override
	public List<OwnUserVO> getUserList(OwnUserVO vo) {
		return ownhomeMapper.getUserList(vo);
	}

	@Override
	public List<OwnUserVO> getPagingUserList(OwnUserVO vo, Paging paging) {
		paging.setTotalRecord(ownhomeMapper.ownUsercount(vo)); // start end		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println(paging);
		vo.setPaging(paging);
		System.out.println("=====페이징하고싶어요======"+paging.toString());
		List<OwnUserVO> list = ownhomeMapper.getUserList(vo);
		list.get(0).setPaging(paging);
		return list;
	}
	
	
	@Override
	public SAccountVO snsLogin(String id) {
		return ownhomeMapper.snsLogin(id);
	}

	 @Override
	   public int updateSnsUser(@Param ("snsNickname") String nickname, @Param ("userId") String id) {
	      return ownhomeMapper.updateSnsUser(nickname, id);
	   }


	@Override
	public int idcheck(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.idcheck(id);
	}

	@Override
	public String searchId(String email) {
		return ownhomeMapper.searchId(email);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username)  {
		// TODO Auto-generated method stub
		OwnUserVO vo = ownhomeMapper.login(username);
		if (vo == null) {
			throw new UsernameNotFoundException(""); 
		}
		return new CustomUser(vo);
	}
	
	@Override
	   public String sendMail(String info, String email){
	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	      
	      final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	      SecureRandom random = new SecureRandom();
       StringBuilder sb = new StringBuilder();

       // 루프의 각 반복은 주어진 문자에서 무작위로 문자를 선택합니다.
       // ASCII 범위를 `StringBuilder` 인스턴스에 추가합니다.

       for (int i = 0; i < 6; i++)
       {
           int randomIndex = random.nextInt(chars.length());
           sb.append(chars.charAt(randomIndex));
       }
       String appNo = sb.toString();
	       try{
	           // 1. 메일 수신자 설정
	           String[] receiveList = {email};
	           System.out.println(info);	           
	           //아이디 찾기
	           if(info.equals("id"))
	           {
	           System.out.println("아이디찾기 들어옴");
	           simpleMailMessage.setTo(receiveList);
	           // 2. 메일 제목 설정
	           simpleMailMessage.setSubject("아이디입니다");
	           // 3. 메일 내용 설정
	           String id = ownhomeMapper.searchId(email);
	           System.out.println("아이디 입니다"+id);
	           simpleMailMessage.setText(id);
	           javaMailSender.send(simpleMailMessage);
	           }
	           else if(info.equals("emailAcc")) {
	        	   simpleMailMessage.setTo(receiveList);
		           // 2. 메일 제목 설정
		           simpleMailMessage.setSubject("인증번호입니다");
		           // 3. 메일 내용 설정
		           simpleMailMessage.setText(appNo);
		           javaMailSender.send(simpleMailMessage);
	           }
	           else
	           {
	              simpleMailMessage.setTo(receiveList);
	              // 2. 메일 제목 설정
	              simpleMailMessage.setSubject("임시비밀번호입니다");
	              // 3. 메일 내용 설정
	              simpleMailMessage.setText(appNo);
	              javaMailSender.send(simpleMailMessage);
	           }   
	           // 4. 메일 전송
	       } catch(Exception e){
	           System.out.println((e.toString()));
	       }
		return appNo;
	   }

	@Override
	public String setPassword(String appNo, String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.setPassword(appNo, id);
	}

	@Override
	public int pwcheck(String pw, String newpw) {
		// TODO Auto-generated method stub
		return ownhomeMapper.pwcheck(pw, newpw);
	}

	@Override
	public QuestionVO selectQuest(String qno) {
		// TODO Auto-generated method stub
		return ownhomeMapper.selectQuest(qno);
	}

	@Override
	public int questionUpdate(QuestionVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.questionUpdate(vo);
	}

	@Override
	public List<QuestionVO> myQuestion(QuestionVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.myQuestion(vo);
	}

	@Override
	public int ownUsercount(OwnUserVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.ownUsercount(vo);
	}

	@Override
	public int myquestionCount(QuestionVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.myquestionCount(vo);
	}

	@Override
	public List<QuestionVO> getPagingmyQuestlist(QuestionVO vo, Paging paging) {
		paging.setTotalRecord(ownhomeMapper.myquestionCount(vo)); // start end		
		paging.setPageUnit(10);
		paging.setPageSize(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println(paging);
		vo.setPaging(paging);
		System.out.println("=====페이징하고싶어요======"+paging.toString());
		List<QuestionVO> list = ownhomeMapper.myQuestion(vo);
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	public int adQuestionCount() {
		// TODO Auto-generated method stub
		return ownhomeMapper.adQuestionCount();
	}

	@Override
	public List<QuestionVO> getPagingAdQuestlist(QuestionVO vo, Paging paging) {
		paging.setTotalRecord(ownhomeMapper.adQuestionCount()); // start end		
		paging.setPageUnit(10);
		paging.setPageSize(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println(paging);
		vo.setPaging(paging);
		System.out.println("=====페이징하고싶어요======"+paging.toString());
		List<QuestionVO> list = ownhomeMapper.questionList(paging);
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	public List<QuestionVO> questionList(Paging paging) {
		// TODO Auto-generated method stub
		return ownhomeMapper.questionList(paging);
	}

	@Override
	public List<ReportVO> reportList(Paging paging) {
		// TODO Auto-generated method stub
		return ownhomeMapper.reportList(paging);
	}

	@Override
	public int reportCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReportVO> getPagingReportList(ReportVO vo, Paging paging) {
		paging.setTotalRecord(ownhomeMapper.reportCount()); // start end		
		paging.setPageUnit(10);
		paging.setPageSize(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println(paging);
		vo.setPaging(paging);
		System.out.println("=====페이징하고싶어요======"+paging.toString());
		List<ReportVO> list = ownhomeMapper.reportList(paging);
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	public ReportVO selectReport(String rno) {
		// TODO Auto-generated method stub
		return ownhomeMapper.selectReport(rno);
	}

	@Override
	public int reportUpdate(ReportVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.reportUpdate(vo);
	}

	@Override
	public List<ChallengeVO> Challenging(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.Challenging(id);
	}

	@Override
	public List<BandMemberDetailVO> Banding(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.Banding(id);
	}

	@Override
	public int ReportCountup(OwnUserVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.ReportCountup(vo);
	}

	@Override
	public int userPermUpdate(OwnUserVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.userPermUpdate(vo);
	}

	@Override
	public List<SAccountVO> getSnsAccountList() {
		// TODO Auto-generated method stub
		return ownhomeMapper.getSnsAccountList();
	}

	@Override
	public int myquestDelete(String rno) {
		// TODO Auto-generated method stub
		return ownhomeMapper.myquestDelete(rno);
	}

	@Override
	public int myinfoupdate(OwnUserVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.myinfoupdate(vo);
	}

}
