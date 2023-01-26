package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CAmountVO {
	String userId;
	String amtType;
	String challNo;
	String amtListNo;
	int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date challAmountDate;
	
	//충전방법
	String payMethod;
}
