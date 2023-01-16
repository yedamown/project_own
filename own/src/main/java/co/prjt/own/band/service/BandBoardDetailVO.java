package co.prjt.own.band.service;

import java.util.Date;


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

}
