package com.bonmoja.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UTFDataFormatException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import com.bonmoja.base.TestBase;

public class TestUtil extends TestBase {

	public static long page_load_timeout = 20;

	public static final String TESTDATA_FILE_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/bonmoja/testdata/BonmojaTestData.xlsx";

	static XSSFWorkbook book;
	static XSSFSheet sheet;
	static Row row;
	static Cell cell;

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static Object[][] getTestdata(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = new XSSFWorkbook(file);
		} catch (UTFDataFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; i < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

}