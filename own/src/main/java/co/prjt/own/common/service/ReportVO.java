package co.prjt.own.common.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reportDate;
	public String status;
	
	public MultimediaVO uploadfile;
	
	String mediaServerFile;
	
	public Paging paging;
	public int first;
	public int last;
}
