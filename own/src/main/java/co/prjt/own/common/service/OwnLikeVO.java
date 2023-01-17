package co.prjt.own.common.service;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnLikeVO {
	public String categoryNo;
	public String userId;
	public String category;
	public String likeNo;
	
	//hjj만듬
	public int likeCount;
}
