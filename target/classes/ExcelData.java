package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	//Identify Testcases coloum by scanning the entire 1st row
	//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
	//after you grab purchase testcase row = pull all the data of that row and feed into test

	public ArrayList<ArrayList<String>> fetchExcelData(String sheetName, String fileLocation) throws IOException
	{
		//fileInputStream argument
		ArrayList<ArrayList<String>> tData = new ArrayList<ArrayList<String>>();

		FileInputStream fis=new FileInputStream(fileLocation); //"D://Mobilenumber.xlsx"
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
	
		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				XSSFSheet sheet=workbook.getSheetAt(i);
				//Identify Testcases coloum by scanning the entire 1st row
			
				Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
				
				
				int k=0;
				while(rows.hasNext())
				{
					tData.add(new ArrayList());
					Row nRow= rows.next();
					Iterator<Cell> ce=nRow.cellIterator();//row is collection of cells
					
					while(ce.hasNext())
					{
						Cell value=ce.next();
						if(value.getCellTypeEnum()==CellType.STRING)
						{
							tData.get(k).add(value.getStringCellValue());
						}
						else if(value.getCellTypeEnum()==CellType.BLANK)
						{
							tData.get(k).add("");

						}
						else
						{
							tData.get(k).add(NumberToTextConverter.toText(value.getNumericCellValue()));
						}
						
					}
					k++;
				}
				
			}
		}
		workbook.close();
		fis.close();
		return tData;

	}

	public ArrayList<String> getData(String colName, String sheetName, String fileLocation) throws IOException
	{
		ArrayList<ArrayList<String>> eData = new ArrayList<ArrayList<String>>();
		eData = fetchExcelData(sheetName, fileLocation);
		
		ArrayList<String> colValue = new ArrayList<String>();
		
		for (int cnt = 0; cnt < eData.get(0).size() ; cnt++)
		{
			if(eData.get(0).get(cnt).equalsIgnoreCase(colName))
			{
				for (int inrCnt = 1; inrCnt < eData.size(); inrCnt++)
				{
					colValue.add(eData.get(inrCnt).get(cnt));
				}
				break;
			}
		}
		
		return colValue;
	}
}

	


