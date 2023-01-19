package co.prjt.own.common.service;

import java.util.Date;

import co.prjt.own.common.Paging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVO {
	public String reportNo;
	public String reporter;
	public String dereporter;
	public String reason;
	public String content;
	Date reportDate;
	public String status;
	
	public Paging paging;
	public int first;
	public int last;
}
