package com.manager.model;

import java.util.List;

public class ManagerService {

	private ManagerDAO_interface dao;

	public ManagerService() {
		dao = new ManagerJDBCDAO();
	}

	public ManagerVO addManager(String managerName, String managerAccount,
			String managerPassword, Integer managerstatus) {

		ManagerVO managerVO = new ManagerVO();

		
		managerVO.setManagerName(managerName);
		managerVO.setManagerAccount(managerAccount);
		managerVO.setManagerPassword(managerPassword);
		managerVO.setManagerstatus(managerstatus);
		dao.insert(managerVO);

		return managerVO;
	}

	public ManagerVO updateManager(Integer managerNo, String managerName, String managerAccount,
			String managerPassword, Integer managerstatus) {

		ManagerVO managerVO = new ManagerVO();

		managerVO.setManagerNo(managerNo);
		managerVO.setManagerName(managerName);
		managerVO.setManagerAccount(managerAccount);
		managerVO.setManagerPassword(managerPassword);
		managerVO.setManagerstatus(managerstatus);
		dao.update(managerVO);

		return managerVO;
	}

	public void deleteManager(Integer managerNo) {
		dao.delete(managerNo);
	}

	public ManagerVO getOneManager(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}

	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
}
