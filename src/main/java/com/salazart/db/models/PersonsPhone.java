package com.salazart.db.models;

import java.util.Date;

public class PersonsPhone {
	private int idPersonPhone;
	private int idPerson;
	private String phone;
	private String textPersonPhone;
	private String curUser;
	private Date curDate = new Date();
	
	public int getIdPersonNumber() {
		return idPersonPhone;
	}
	public void setIdPersonPhone(int idPersonPhone) {
		this.idPersonPhone = idPersonPhone;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTextPersonPhone() {
		return textPersonPhone;
	}
	public void setTextPersonPhone(String textPersonPhone) {
		this.textPersonPhone = textPersonPhone;
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
