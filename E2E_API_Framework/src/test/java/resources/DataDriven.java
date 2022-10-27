package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	public ArrayList<String> getData(String testcasename) throws IOException {
		
		String filepath = System.getProperty("user.dir")+"\\Datasheet.xlsx";
		ArrayList<String> valuesList = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(filepath);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int sheetsCount = wb.getNumberOfSheets();

		for (int i=0; i<sheetsCount; i++) {
			
			if (wb.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				
				XSSFSheet sheet = wb.getSheetAt(i);
				
				//Scan entire row to get to the desired 'Testcases' cell
				Iterator<Row> rows = sheet.rowIterator();
				Row firstRow = rows.next();
				
				Iterator<Cell> cells = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					
					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						
						column=k;
					}
					k++;
				} 
				//System.out.println(column);
				
				//Once 'Testcases' cell is identified scan entire column to find 'Purchase' row
				while (rows.hasNext()) {
					
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
						//Now once Purchase row is found, get complete data of this row and store into array
						Iterator<Cell> cv = r.cellIterator();
						
						while(cv.hasNext()) {
							Cell c = cv.next();
							CellType cellType = c.getCellType();
							
							if (cellType==CellType.STRING) {
								valuesList.add(c.getStringCellValue());
							} else {
								String stringValue = NumberToTextConverter.toText(c.getNumericCellValue());
								valuesList.add(stringValue);
							}

							//System.out.print(valuesList);
							
						}
					}
				}
			}
		}
		return valuesList;
	}

	
	
}


