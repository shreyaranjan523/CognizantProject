package utilities;

import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ApachePOI {
//new File("C:\\Users\\2320135\\eclipse-workspace\\HackathonProject\\apachepoi1.xlsx"));
public Object[][] getData(String path,String filename,String sheetname) throws Exception
{
	String directoryPath = System.getProperty("user.dir");
	String relativePath = "/src/test/resources/ExcelSheets/TestData1.xlsx";
	FileInputStream file = new FileInputStream(directoryPath+relativePath);
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet sheet = workbook.getSheet(sheetname);
	int totalRows = sheet.getLastRowNum();
	int totalcells = sheet.getRow(0).getLastCellNum();
	Object[][] formData = new Object[totalRows][totalcells];
	for(int i = 0;i < totalRows;i++) {
		XSSFRow row = sheet.getRow(i+1);
		for(int j = 0;j < totalcells;j++) {
			XSSFCell cell = row.getCell(j);
			formData[i][j] = cell.toString();
		}
	}
	workbook.close();
	file.close();
	return formData;
}
}
