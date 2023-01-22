package co.prjt.own.band.service;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.Paging;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BandCalendarVO{
	
	public String bandCalendarNo;
	public String bandBoardDetailNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date bandCalendarStart;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date bandCalendarEnd;
	public String bandCalendarTitle;
	public String bandCalendarMark;
	public String bandCalendarLocation;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date bandCalendarOptionDay;
	public String bandCalendarOption;
	public String bandCalendarOptionShare;
}
