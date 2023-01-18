package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.service.MultimediaVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandBoardDetailVO {
	public String bandBoardContent;
	public int bandBoardCnt;
	public String bandBoardDetailNo;
	public String bandRemarks;
	@JsonFormat(pattern = "yyyy년MM월dd일 HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date bandBoardDate;
	Date bandBoardUpdate;
	public String bandBoardTag;
	public String bandBoardOptionNo;
	public String bandBoardWriter;
	public String bandBoardTitle;
	//이미지 주소
	public List<MultimediaVO> bandImgs;
	public MultimediaVO bandImg;
	
	public Integer first = 1;
	public Integer last = 10;
	
}
