package com.salazart.gui.model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.salazart.TelBookParser;
import com.salazart.gui.services.WindowService;

public class TelBookParserUI{
	
	private static Logger log = LogManager.getRootLogger();
	private static TelBookFrame mainFrame;
	
	private static TelBookParserUI instance;
	
    public static TelBookParserUI getInstance() {
    	TelBookParser.log4j.info("===================Start TelBookParser=================");
        if (instance == null) {
        	synchronized(TelBookParserUI.class) {
        		if(instance == null) {
        			instance = new TelBookParserUI();
        		}
        	}
        }
        return instance;
    }
    
	/**
	 * Launch the application.
	 */
	public static void showFrame(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					log.error(e);
				}
			}
		});
	}
	
	public static void showFrame(){
		mainFrame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public TelBookParserUI() {
		//showFrame(null);
		mainFrame = new TelBookFrame();
		mainFrame.addWindowListener(new WindowService());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addLog(String logText) {
		mainFrame.addLog(logText);
	}
	
	public void clearLog(){
		mainFrame.clearLog();
	}
}
