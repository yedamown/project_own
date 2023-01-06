package co.prjt.own.ownhome.service;

import java.util.List;

public interface OwnhomeService {
	public List<EmpVO> getEmpList(EmpVO empVO);
	public int insertUser(OwnUserVO UserVO);
}
