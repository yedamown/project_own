package co.prjt.own.band.service;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandBoardOptionVO {
	public int bandBoardOrder;
	public String bandBoardName;
	public String bandBoardAuth;
	public String bandLikeOption;
	public String bandContentOption;
	public String bandBoardRemarks;
	public int bandBoardLine;
	public String bandBoardOptionNo;
	public String bandNo;

	public int boardNumber;
}
