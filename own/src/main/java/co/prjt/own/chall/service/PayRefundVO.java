package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PayRefundVO {
	String payRefundNo;
	String payType;
	int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date payDate;
	
	String payCode;
	String refundInfo;
}
