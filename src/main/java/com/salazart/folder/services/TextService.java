package com.salazart.folder.services;

public class TextService {
	public String clearText(String text){
		if(text != null && !text.isEmpty()){
			text = text.trim();
			text = text.replace("..",".");
			text = text.replace(" ,",",");
			text = text.replace("\\","/");
			text = text.replaceAll("Вул.","вул. ");
			text = text.replaceAll("  "," ");
			return text.replaceAll("[^а-я^А-Я^a-z^A-Z^0-9ЄєіІЇї\\()_.,-/ ]+","");
		} else {
			return "";
		}
	}
}
