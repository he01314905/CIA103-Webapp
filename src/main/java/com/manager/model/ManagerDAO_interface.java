package com.manager.model;

import java.util.*;

public interface ManagerDAO_interface {
          public void insert(ManagerVO managerVO);
          public void update(ManagerVO managerVO);
          public void delete(Integer managerNo);
          public ManagerVO findByPrimaryKey(Integer managerNo);
          public List<ManagerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
		public ManagerVO findAP(String managerAccount, String managerPassword);
}
