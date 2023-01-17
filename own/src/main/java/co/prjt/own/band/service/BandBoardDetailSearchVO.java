package co.prjt.own.band.service;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.service.OwnLikeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandBoardDetailSearchVO extends BandBoardDetailVO{
	//같이 불러올 댓글 수, 마음수
	public Integer like;
	public Integer reply;
	//마음을 담을 곳
	OwnLikeVO likeList;
	
	public String bandNo;
	
	public String bandBoardWriter;
	public String bandBoardTitle;
	public String bandBoardContent;
	public int bandBoardCnt;
	public String bandBoardDetailNo;
	public String bandRemarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date bandBoardDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date bandBoardUpdate;
	public String bandBoardTag;
	public String bandBoardOptionNo;
	//이미지 주소
	public List<MultimediaVO> bandImgs;
	public MultimediaVO bandImg;
	public String bandBoardTime;
	public Integer first = 1;
	public Integer last = 10;
}
