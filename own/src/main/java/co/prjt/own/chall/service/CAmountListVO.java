package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CAmountListVO {
	String userId;
	String amtType;
	String challNo;
	String amtListNo;
	int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date challAmountDate;
}
