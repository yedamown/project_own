package co.prjt.own.chall.service;

import lombok.Data;

@Data
//챌린지 도전결과
public class CResultVO {
	String resultNo;  
	String userId;
	String challNo;
	
	int memVld; // 회원이 완료한 인증횟수
	int challVld; //도전 인증횟수라 숫자
	
	int successRate; //횟수를 통해서 계산한 도전성공률
	int refundPrice; //도전환급금
	int rewardPrice; //100% 달성시 받는 상금
}
