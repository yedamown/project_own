package co.prjt.own.chall;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import groovy.util.logging.Log4j;

@SpringBootTest
@Log4j
class ChallApplicationTest {

	@Autowired ChallengeService challenge;
	
	//도전등록 처리
	//@Test
	/*
	 * public String insertProc(ChallengeVO vo) { challenge.insertChall(vo); return
	 * "content/chall/challHome"; }
	 */
	
	
	//상세페이지 가기
	//@Test
	public void detailChall(){
		ChallengeVO vo = new ChallengeVO();
		vo.setChallNo("CHA_1");
		ChallengeVO result = challenge.getChall(vo);
		System.out.println(result);
	}
	

	
}
