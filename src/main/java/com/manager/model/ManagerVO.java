package com.manager.model;
import java.sql.Date;

public class ManagerVO implements java.io.Serializable{
	private Integer managerNo;
	private String managerName;
	private String managerAccount;
	private String managerPassword;
	private Integer managerstatus;
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public Integer getManagerstatus() {
		return managerstatus;
	}
	public void setManagerstatus(Integer managerstatus) {
		this.managerstatus = managerstatus;
	}
	
	
	
}
