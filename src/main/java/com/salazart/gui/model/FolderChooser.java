package com.salazart.gui.model;

import java.io.File;

import javax.swing.JFileChooser;

import com.salazart.folder.services.PropertyService;


/**
 * This class create user dialog for select folder and return selected folder
 * @author dr
 *
 */
public class FolderChooser {
	private final static String PROPERTIES_VALUE = "defaultFolder";
	private final static String EMPTY_FOLDER = "";
	private String defaultDirectory = getDefaultFolder();;

	/**
	 * Check properties and return path directory 
	 * @return
	 */
	private String getDefaultFolder(){
		String defaultDirectory = PropertyService.getValueProperties(PROPERTIES_VALUE);
		if(new File(defaultDirectory).isDirectory()){
			return defaultDirectory;
		} else {
			return System.getProperty("user.dir");
		}
	}
	/**
	 * Show gui FileChooser for select folder
	 * @param pathFolder
	 * @return
	 */
	private String guiOpenDirectory(String pathFolder) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File(pathFolder));

		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if(chooser.getSelectedFile().isDirectory()){
				PropertyService.setValueProperties(PROPERTIES_VALUE, chooser.getSelectedFile().toString());
				return chooser.getSelectedFile().toString();
			} else {
				PropertyService.setValueProperties(PROPERTIES_VALUE, chooser.getCurrentDirectory().toString());
				return chooser.getCurrentDirectory().toString();
			}
		} else {
			return EMPTY_FOLDER;
		}
	}
	
	/**
	 * Returning selected directory
	 * @return
	 */
	public String getSelectedDirectory() {
		return guiOpenDirectory(defaultDirectory);
	}
}
