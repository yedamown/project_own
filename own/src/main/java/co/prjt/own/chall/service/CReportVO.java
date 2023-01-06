package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CReportVO {
	String reportNo;
	String vldNo;
	String reporter;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date reportDate;
	
	String reportReason;
	String reportStatus;
	String reportResult;
}
