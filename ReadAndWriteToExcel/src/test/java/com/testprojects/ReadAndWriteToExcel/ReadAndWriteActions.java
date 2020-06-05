package com.testprojects.ReadAndWriteToExcel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteActions {
	
	public  WebDriver driver;
	public  Screen screen;	
	List<List<String>> tableData = new ArrayList<List<String>>();
	
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");	
		driver = new ChromeDriver();
		screen = new Screen();
	}
	
	public void launchURL()
	{
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().window().maximize();
	}
	
	public void closeDriver() {
		driver.close();
		
	}
	

		
		
public  ArrayList<String> readFile(String filepath, String fileName) throws IOException{
			
			String[] arr = {filepath, fileName};
			String path= arr[0];
			String sheetName = arr[1];
			
			System.out.println("path: ..... "+path);
			System.out.println("sheetno: ..... "+sheetName);
			
			ArrayList<String> myData = new ArrayList<String>();				
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			Iterator<Row> rowItr = sheet.iterator();
			
			while(rowItr.hasNext()) {
				Row row = rowItr.next();
				Iterator<Cell> cellItr = row.cellIterator();
				
				while(cellItr.hasNext()) {
					Cell cell = cellItr.next();
					String val = cell.getStringCellValue();
					myData.add(val);
					
				}
			}
			System.out.println("Data on Excel : "+myData);
			return myData;
			
		}

public List<List<String>> getTableDataByRows() {
		WebElement tableRow;
		int size = driver.findElements(By.xpath("//table[@id='customers']//tr/th | //table[@id='customers']//tr/td")).size();
		int columns = driver.findElements(By.xpath("//table[@id='customers']//tr/th")).size();
		int rows = driver.findElements(By.xpath("//table[@id='customers']//tr")).size();
		int cellNum = 0;		
		System.err.println(rows+" "+columns);
		
		for(int i=0; i<rows; i++) {	
			
			List<String> rowElements = new ArrayList<String>();
			for(int j=0; j<columns; j++) {	
				tableRow = driver.findElements(By.xpath("//table[@id='customers']//tr/th | //table[@id='customers']//tr/td")).get(cellNum);
				rowElements.add(tableRow.getText().toString());
				cellNum++;				
			}
			System.err.println(rowElements);
			tableData.add(rowElements);
				
			}
		return tableData;	
}

public void writeDataToExcelFile(List data, String fileName) throws IOException {
	FileOutputStream out = new FileOutputStream(new File(fileName));
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Employee Data");
    
    Iterator <List<String>>i = data.iterator();
    int rownum=0;
    int cellnum = 0;
    while (i.hasNext()) {
        List<String> templist = (List<String>) i.next();
        Iterator<String> tempIterator= templist.iterator();
        Row row = sheet.createRow(rownum++);
        cellnum = 0;
        while (tempIterator.hasNext()) {
            String temp = (String) tempIterator.next();
                Cell cell = row.createCell(cellnum++);
                        cell.setCellValue(temp);
            }

        }
    workbook.write(out);
    out.close();
    workbook.close();
}


		

		
		


	
}
