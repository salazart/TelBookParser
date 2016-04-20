package com.salazart.folder.services;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileService {
	private final static String FINISH_FOLDER = "finished_tel";
	
	private Logger log = LogManager.getRootLogger();
	
	private String[] extensions = new String[] { "xml", "zip" };

	public List<File> getFilesFromFolder(String folderPath) {
		File folder = new File(folderPath);
		if (folder != null && folder.isDirectory()) {
			return Arrays.asList(folder.listFiles(getFileFilter()));
		}
		return new ArrayList<File>();
	}

	private FileFilter getFileFilter() {
		return new FileFilter() {
			public boolean accept(File pathname) {
				String path = pathname.getAbsolutePath().toLowerCase();
				for (String extension : extensions) {
					if (path.endsWith(extension)) {
						return true;
					}
				}
				return false;
			}
		};
	}
	
	public File moveFile(File file) {
		File finishFolder = getFinishFolder(file);
		log.info("Moving file: " + file + " to folder: " + finishFolder);
		createFolder(finishFolder);
		File finishFile = getFinishFile(file, finishFolder);
		moveFile(file, finishFile);
		return finishFile;
	}

	private File getFinishFile(File file, File finishFolder) {
		String finishNameFile = 
				finishFolder
				+ File.separator
				+ file.getName();
		File finishFile = new File(finishNameFile);
		return finishFile;
	}

	private void moveFile(File file, File finishFile) {
		if(file.renameTo(finishFile)){
			log.info("File moved successfully: " + finishFile.getAbsolutePath());
		} else {
			log.info("File don't moved: " + file.getAbsolutePath());
			if(file.delete()){
				log.info("File deleted successfully: " + file.getAbsolutePath());
			} else {
				log.info("File don't deleted: " + file.getAbsolutePath());
			}
		}
	}

	private File getFinishFolder(File file) {
		String finishNameFolder = 
				file.getParentFile().toString()
				+ File.separator 
				+ FINISH_FOLDER;
		return new File(finishNameFolder);
	}
	
	private void createFolder(File file){
		if(!file.isDirectory()){
			if(file.mkdir()){
				log.info("Folder created successfully: " + file.getAbsolutePath());
			}
		}
	}
}
