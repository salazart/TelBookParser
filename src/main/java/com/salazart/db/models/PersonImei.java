package com.salazart.db.models;

import java.util.Date;

public class PersonImei {
	private int idPersonImei;
	private int idPerson;
	private String imei;
	private String textPersonImei;
	private String curUser;
	private Date curDate;
	
	public int getIdPersonImei() {
		return idPersonImei;
	}
	public void setIdPersonImei(int idOsobaImei) {
		this.idPersonImei = idOsobaImei;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getTextPersonImei() {
		return textPersonImei;
	}
	public void setTextPersonImei(String textPersonImei) {
		this.textPersonImei = textPersonImei;
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
