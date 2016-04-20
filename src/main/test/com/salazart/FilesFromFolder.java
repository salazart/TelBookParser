package com.salazart;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.salazart.folder.services.FileService;

public class FilesFromFolder {
	
	public static void main(String[] args) {
		FileService fileService = new FileService();
		List<File> files = fileService.getFilesFromFolder(System.getProperty("user.dir"));
		
		File file = files.get(0);
		
		String finishNameFolder = 
				file.getParentFile().toString() 
				+ File.separator 
				+ "finished_tel";
		
		String finishNameFile = 
				file.getParentFile().toString() 
				+ File.separator 
				+ "finished_tel" 
				+ File.separator
				+ file.getName();
		
		File finishFolder = new File(finishNameFolder);
		if(finishFolder.isDirectory()){
			File finishFile = new File(finishNameFile);
			file.renameTo(finishFile);
		} else {
			finishFolder.mkdir();
			File finishFile = new File(finishNameFile);
			file.renameTo(finishFile);
		}
		
	}

}

