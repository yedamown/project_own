package co.prjt.own.ownhome.mapper;

import java.util.List;

import co.prjt.own.ownhome.service.EmpVO;
import co.prjt.own.ownhome.service.OwnUserVO;

public interface OwnhomeMapper {
	public List<EmpVO> getEmpList(EmpVO empVO);
	public int insertUser(OwnUserVO UserVO);
}
