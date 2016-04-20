package com.salazart.folder.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;

public class XMLPropertyService {
	private static final String FILE_ENCODING = "UTF8";
	private Logger log = LogManager.getRootLogger();
	private String formatXml(String xml) {
		try{
	         Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
	         
	         serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	         serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         
	         Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	         StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
	         
	         serializer.transform(xmlSource, res);
	         
	         return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
	         
	      }catch(Exception e){
	    	  log.error(e);
	         return xml;
	      }
	}
	
	public void writeXMLFile(String fileName, String text) {
		text = formatXml(text);
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(fileName, false));
			out.println(text);
			out.close();
		} catch (FileNotFoundException e) {
			log.error(e);
		}		
	}
	
	public String readXMLFile(File file){
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(file), FILE_ENCODING));
			String currentLine;
		    while ((currentLine = in.readLine()) != null) {
				sb.append(currentLine);
			}
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}

	    return sb.toString();
	}
}
