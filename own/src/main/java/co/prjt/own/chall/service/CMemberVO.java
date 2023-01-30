package co.prjt.own.chall.service;

import lombok.Data;

@Data
public class CMemberVO {
	String memNo;
	String userId;
	String memNickname;
	String memIntro;
	
	//현재 예치금
	int memAmt;
	//환불 계좌번호
	int memAcc;
	//환불 계좌예금주
	String memAccname;
	//환불계좌 은행
	String memBank;

	//프사 경로
	String memImage;
	//결제 충전한 금액
	int price;
}
