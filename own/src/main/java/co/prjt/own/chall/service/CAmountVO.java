package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import co.prjt.own.common.Paging;
import lombok.Data;

@Data
public class CAmountVO {
	String userId;
	String amtType;
	//방법 추가 - 충전은 카드/카카오, 환급은 계좌
	String amtMethod;
	String challNo;
	String amtListNo;
	int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date challAmountDate;
	
	//페이징관련정보
	int first;
	int last;
	Paging paging;
}
