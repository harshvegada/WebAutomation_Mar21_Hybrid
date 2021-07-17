package com.qa.tc.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.tc.orangehrm.constant.ConstantPath;

public class ExcelOperation {

	public static Object[][] getSheetData(String fileName, String sheetName, boolean isHeader) {
		int count = 0;
		if (isHeader)
			count = 1;

		File file = new File(ConstantPath.TESTDATA + "/" + fileName);
		FileInputStream inputStream;
		Workbook wb = null;
		try {
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum() + 1;
		if (isHeader)
			totalRows = sheet.getLastRowNum();
		System.out.println("total rows -> " + totalRows); // 0->3

		int totalCol = sheet.getRow(0).getLastCellNum();
		System.out.println("total col -> " + totalCol); // 3

		Object[][] data = new Object[totalRows][totalCol];

		for (int rowIndex = 0 + count; rowIndex <= data.length; rowIndex++) {
			for (int colIndex = 0; colIndex < totalCol; colIndex++) {
				try {
					if (CellType.STRING == sheet.getRow(rowIndex).getCell(colIndex).getCellType())
						data[rowIndex - count][colIndex] = sheet.getRow(rowIndex).getCell(colIndex)
								.getStringCellValue();
					else if (CellType.NUMERIC == sheet.getRow(rowIndex).getCell(colIndex).getCellType())
						data[rowIndex - count][colIndex] = sheet.getRow(rowIndex).getCell(colIndex)
								.getNumericCellValue();
					else if (CellType.BOOLEAN == sheet.getRow(rowIndex).getCell(colIndex).getCellType())
						data[rowIndex - count][colIndex] = sheet.getRow(rowIndex).getCell(colIndex)
								.getBooleanCellValue();
				} catch (NullPointerException ne) {
					data[rowIndex - count][colIndex] = "";
				}
				System.out.println(data[rowIndex - count][colIndex]);
			}
			System.out.println();
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static List<String> getAllRowsAtColIndex(String fileName, String sheetName, int colIndex, boolean isHeader) {
		List<String> allRowsAtIndex = new ArrayList<String>();
		File file = new File(ConstantPath.TESTDATA + "/" + fileName);
		FileInputStream inputStream;
		Workbook wb = null;
		try {
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum() + 1;

		int startIndex = isHeader ? 1 : 0;

		for (int index = startIndex; index < totalRows; index++) {
			String data = sheet.getRow(index).getCell(colIndex).getStringCellValue();
			allRowsAtIndex.add(data);
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allRowsAtIndex;
	}

}
