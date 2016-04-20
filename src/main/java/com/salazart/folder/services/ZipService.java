package com.salazart.folder.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipService {
	private static final String FILE_ZIP= ".zip";
	
	private static final String FILE_XML= ".xml";
	
	private Logger log = LogManager.getRootLogger();
	
	public ZipService() {
		
	}
	
	public void createZipFile(String inFileName){
		File fileName = new File(inFileName);
		String zipFileName = StringUtils.substringBeforeLast(fileName.getName(), ".") + FILE_ZIP;
		
		InputStream is = null;
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			
			ZipParameters zipParameters = createZipParameters(fileName.getName());
			
			is = new FileInputStream(fileName);
			zipFile.addStream(is, zipParameters);
			
		} catch (ZipException | FileNotFoundException e) {
			log.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
					deleteFile(fileName);
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	private void deleteFile(File fileName) {
		Path path = fileName.toPath();
		try {
			Files.deleteIfExists(path);
			log.debug("File " + fileName + " deleted successfully");
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	private ZipParameters createZipParameters(String fileName){
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setFileNameInZip(fileName);
		parameters.setSourceExternalStream(true);
		
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		
		parameters.setPassword(createName());
		return parameters;
	}
	
	public File extractZipFile(File zipFileName){
		if(!zipFileName.getName().endsWith(FILE_ZIP)){
			return zipFileName;
		}
		
		String xmlFileName = StringUtils.substringBeforeLast(zipFileName.getName(), ".") + FILE_XML;
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(createName());
			}
			zipFile.extractFile(xmlFileName, zipFileName.getParentFile().toString());
			log.debug("File " + xmlFileName + " extracted successfful");
		} catch (ZipException e) {
			log.error(e);
		} finally {
			deleteFile(zipFileName);
		}
		return new File(zipFileName.getParentFile() + File.separator + xmlFileName);
	}
	
	private String createName(){
		String name = "4";
		for(int i = 1; i < 3; i++){
			name = name + "mr3";
		}
		name = name +"89";
		return name;
	}
}
