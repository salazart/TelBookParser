package com.salazart.db.models;

import java.util.Date;

public class PersonAddr {
	private int idPersonAddr;
	private int idPerson;
	private String addr;
	private String textPersonAddr;
	private String curUser;
	private Date curDate = new Date();

	public boolean isEmpty(){
		return idPerson == 0 || getAddr().isEmpty();
	}
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getIdPersonAddr() {
		return idPersonAddr;
	}

	public void setIdPersonAddr(int idPersonAddr) {
		this.idPersonAddr = idPersonAddr;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getTextPersonAddr() {
		return textPersonAddr;
	}

	public void setTextPersonAddr(String textPersonAddr) {
		this.textPersonAddr = textPersonAddr;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	
}
