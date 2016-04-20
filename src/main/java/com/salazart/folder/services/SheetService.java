package com.salazart.folder.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.salazart.folder.models.Contact;

/**
 * This class handle sheet of xls document
 * 
 * @author dr
 * 
 */
public class SheetService{
	public Logger log = LogManager.getRootLogger();
	private PhoneService phoneService = new PhoneService();
	
	public List<String> getPhones(Sheet sheet){
		List<String> phones = new ArrayList<String>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			String phone = "";
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String temptext = textOfCell(cell);
				phone = phoneService.modifyPhone(temptext);
				if(!phone.isEmpty()){
					phones.add(phone);
				}
			}
		}
		return phones;
	}
	
	public List<String> getAllPhone(HSSFSheet sheet){
		List<String> phones = new ArrayList<String>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			String phone = "";
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String temptext = textOfCell(cell);
				phone = phoneService.modifyPhone(temptext);
				if(!phone.isEmpty()){
					phones.add(phone);
				}
			}
		}
		return phones;
	}
	/**
	 * This method returning all numbers from file
	 * 
	 * @param sheet
	 * @return
	 */
	public List<Contact> getContacts(Sheet xssfSheet) {
		
		List<Contact> contacts = new ArrayList<Contact>();
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = xssfSheet.iterator();
		//Contact contact = new Contact();
		while (rowIterator.hasNext()) {
			String name = "";
			String phone = "";
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// Check the cell type and format accordingly
				String temptext = textOfCell(cell);
				log.debug(temptext);
			}
		}
		return contacts;
	}

	/**
	 * Returning next value from cell of sheet
	 * 
	 * @param sheet
	 * @param text
	 * @return
	 */
	public String getValueNextCell(Sheet sheet, String text) {
		boolean flag_next_cell = false;
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			String tempText = null;
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (flag_next_cell == true) {
					return textOfCell(cell);
				} else {
					tempText = textOfCell(cell);
					if (tempText.equals(text)) {
						flag_next_cell = true;
					}
				}
			}
		}
		return null;
	}

	/**
	 * This method returning value with row and column
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getValueFromIndex(Sheet sheet, int row, int column) {
		String value = new String();
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row temp_row = rowIterator.next();
			if (temp_row.getRowNum() != row) {
				continue;
			};
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = temp_row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getColumnIndex() != column) {
					continue;
				} else {
					return textOfCell(cell);
				}
			}
		}
		return value;
	}

	/**
	 * Reading value of cell
	 * 
	 * @param cell
	 * @return
	 */
	private static String textOfCell(Cell cell) {
		// Check the cell type and format accordingly
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil
						.getJavaDate(cell.getNumericCellValue());
				return date.toString();
			} else {
				long numeric = (long) cell.getNumericCellValue();
				return String.valueOf(numeric);
			}
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return "";
		}
	}
}
