package co.prjt.own.common.service;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnLikeVO {
	public String likeNo;
	public String userId;
	public String categoryNo;
	public String category;
	
	//hjj만듬
	public int likeCount;
}
