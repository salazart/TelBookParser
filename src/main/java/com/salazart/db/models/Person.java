package com.salazart.db.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
	private int idPerson;
	private String lastName = "";
	private String firstName = "";
	private String patrName = "";
	private boolean booArh;
	private int idSection = 22;
	private String curUser;
	private Date curDate = new Date();
	private Date datePerson = new Date();
	private Date aliasPerson;
	private Date idTypRight;
	private String textPerson = "";
	private int brtd;
	private boolean booCas = false;
	
	public boolean isEmpty(){
		return getLastName().isEmpty() && getFirstName().isEmpty() && getPatrName().isEmpty();
	}
	
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String nameOsoba) {
		this.firstName = nameOsoba;
	}
	public String getPatrName() {
		return patrName;
	}
	public void setPatrName(String patrName) {
		this.patrName = patrName;
	}
	public boolean isBooArh() {
		return booArh;
	}
	public void setBooArh(boolean booArh) {
		this.booArh = booArh;
	}
	public int getIdSection() {
		return idSection;
	}
	public void setIdSection(int idSection) {
		this.idSection = idSection;
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
	public Date getDatePerson() {
		return datePerson;
	}
	public String getDatePersonTimeStamp() {
		return parseDateToTimeStamp(datePerson);
	}
	public void setDatePerson(Date datePerson) {
		this.datePerson = datePerson;
	}
	public Date getAliasPerson() {
		return aliasPerson;
	}
	public void setAliasPerson(Date aliasPerson) {
		this.aliasPerson = aliasPerson;
	}
	public Date getIdTypRight() {
		return idTypRight;
	}
	public void setIdTypRight(Date idTypRight) {
		this.idTypRight = idTypRight;
	}
	public String getTextPerson() {
		return textPerson;
	}
	public void setTextPerson(String textPerson) {
		this.textPerson = textPerson;
	}
	public int getBrtd() {
		return brtd;
	}
	public void setBrtd(int brtd) {
		this.brtd = brtd;
	}
	public boolean getBooCas() {
		return booCas;
	}
	public void setBooCas(boolean booCas) {
		this.booCas = booCas;
	}
	private String parseDateToTimeStamp(Date date){
		final String DATE_OUT = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(DATE_OUT);
		String timeStampDate = df.format(date);
		return timeStampDate;
	}
}
