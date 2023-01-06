package co.prjt.own.sns.mapper;

import java.util.List;

import co.prjt.own.sns.service.SAccountVO;

public interface SnsMapper {
	public List<SAccountVO> getSnsUserList(SAccountVO SAccountVO);

}
