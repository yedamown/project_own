package co.prjt.own.sns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;
import co.prjt.own.sns.service.SBoardService;
import co.prjt.own.sns.service.SBoardVO;

@SpringBootTest
public class SnsApplicationTest {

		@Autowired SBoardService boardService;
		@Autowired OwnhomeService ownService;
		@Autowired SAccountService snsService;
		//@Test 게시글쓰기 테스트 
		public void insetBoard(){
			SBoardVO svo = new SBoardVO();
			svo.setSnsAccountNo("SAU_1");
			svo.setSnsBoardContent("테스트내용");
			boardService.insertSnsBoard(svo);
			System.out.println(svo);
		}
		
		//SNS간편가입
		@Test
		public void insertSnsUser() {
			SAccountVO svo = new SAccountVO();
			OwnUserVO ovo = new OwnUserVO();
			svo.setSnsNickname("test");
			snsService.insertSnsUser(svo);
			ownService.updateSnsUser(svo.getSnsNickname(), "testest");
			
		}
}
