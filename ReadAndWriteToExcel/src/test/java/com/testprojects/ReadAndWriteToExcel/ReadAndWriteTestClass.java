package com.testprojects.ReadAndWriteToExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ReadAndWriteTestClass  {

	ReadAndWriteActions readWriteActions;
	List<List<String>> TableData = new ArrayList<List<String>>();
	
	
	@BeforeClass
	public void beforeClass(){
		readWriteActions = new ReadAndWriteActions();
		readWriteActions.setDriver();
	}
	@Test
	public void launchURL() {
		readWriteActions.launchURL();
	}
	
	@Test(dependsOnMethods = "launchURL")
	public void getDataFromTable() {
		TableData = readWriteActions.getTableDataByRows();
		Assert.assertFalse(TableData.isEmpty());
	}
	
	@Test(dependsOnMethods = "getDataFromTable")
	public void writeDataInExcelFile() throws IOException {
		readWriteActions.writeDataToExcelFile(TableData, "src\\test\\resources\\ExcelFiles\\UserData.xlsx");
	}
	
	@Test(dependsOnMethods = "writeDataInExcelFile")
	public void readDAtaFromExcelFile() throws IOException {
		readWriteActions.readFile("src\\test\\resources\\ExcelFiles\\UserData.xlsx", "Employee Data");
	}
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		readWriteActions.closeDriver();
	}
 
}
