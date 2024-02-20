package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {
	DataFormatter formatter= new DataFormatter();
	
	@Test(dataProvider="driveTest")
	public void testCaseData(String greetings, String Communication,String id)
	{
	System.out.println(greetings + Communication+ id );	
	}
	
	@DataProvider(name="driveTest")
	public  Object[][] getData() throws IOException
	{
		//Object[][]data= {{"hello","text","1"},{"bye","message","14"},{"solo","call","45"}};
		//return data; 4
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Documents\\excelDriven.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		int rowCount=sheet.getPhysicalNumberOfRows();
		 XSSFRow row=sheet.getRow(0);
		 int columnCount=row.getLastCellNum();
		 Object data[][]=new Object[rowCount-1][columnCount];
		for (int i=0;i<rowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<columnCount;j++)
			{
				//System.out.println(row.getCell(j));
				XSSFCell cell=row.getCell(j);
					
				data[i][j]= formatter.formatCellValue(cell);
			}
		}
		return data;
		 
	}

}
