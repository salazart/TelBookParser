package com.salazart.db.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("section") 
public class Section {
	
	@XStreamAlias("id")
	private int idSection;
	
	@XStreamAlias("name")
	private String nameSection;

	public int getIdSection() {
		return idSection;
	}

	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}

	public String getNameSection() {
		return nameSection;
	}

	public void setNameSection(String textSection) {
		this.nameSection = textSection;
	}

}
