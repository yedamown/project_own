package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

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
	Date bandBoardDate;
	Date bandBoardUpdate;
	public String bandBoardTag;
	public String bandBoardOptionNo;
	//이미지 주소
	public List<MultimediaVO> bandImgs;
	public MultimediaVO bandImg;
	
	//더보기 용으로 쓸 예정
	public Integer one = 1; //첫페이지
	public Integer five = 5; //마지막
	public Integer multi = 1; //곱하기2
	//같이 불러올 댓글 수, 마음수
	public Integer like;
	public Integer reply;
	
	public Integer first = 1;
	public Integer last = 10;
	
	//더보기용...
	public void setOne(int multi) {
		this.one = one*multi;
	}
	
	public void setFive(int multi) {
		this.five = five*multi;
	}
}
