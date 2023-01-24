package co.prjt.own.ownhome.service;

import java.util.Date;

import co.prjt.own.common.Paging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVO {
	public String questNo;
	public String userId;
	public String title;
	public String content;
	public String answer;
	Date questDate;
	public String header;
	public String status;
	
	public String rn;
	public Paging paging;
	public int first;
	public int last;
}
