package co.prjt.own.ownhome.service.impl;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.CustomUser;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

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
	public String snsLogin(String id) {
		return ownhomeMapper.snsLogin(id);
	}

	@Override
	public int updateSnsUser(String id) {
		return ownhomeMapper.updateSnsUser(id);
	}

	@Override
	public int idcheck(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.idcheck(id);
	}

	@Override
	public void searchId(String email) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public UserDetails loadUserByUsername(String username)  {
		// TODO Auto-generated method stub
		OwnUserVO vo = ownhomeMapper.login(username);
		System.out.println("체크==="+vo);
		if (vo ==null) {
			throw new UsernameNotFoundException(""); 
		}
		return new CustomUser(vo);
	}
	
	@Override
	public void sendMail(){
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

	    try{
	        // 1. 메일 수신자 설정
	        String[] receiveList = {"dodgo12@naver.com"};
	        
	        //인증번호 생성
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
	        // ArrayLis의 경우 배열로 변환이 필요함
	        /*
	        ArrayList<String> receiveList = new ArrayList<>();
	        receiveList.add("test@naver.com");
	        receiveList.add("test@gmail.com");
	        String[] receiveList = (String[]) receiveList.toArray(new String[receiveList.size()]);
	        */
	        simpleMailMessage.setTo(receiveList);
	        // 2. 메일 제목 설정
	        simpleMailMessage.setSubject("임시비밀번호입니다");
	        // 3. 메일 내용 설정
	        simpleMailMessage.setText(appNo);

	        // 4. 메일 전송
	        javaMailSender.send(simpleMailMessage);
	    } catch(Exception e){
	        System.out.println((e.toString()));
	    }
	}

	@Override
	public String setPassword(String appNo, String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.setPassword(appNo, id);
	}

}
