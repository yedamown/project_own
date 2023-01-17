package co.prjt.own.common.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReportVO {
	public String reportNo;
	public String reporter;
	public String dereporter;
	public String reason;
	public String content;
	Date reportDate;
	public String status;	
}
