package com.maputility.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	@GetMapping("/")
	public String redirect(Model model) throws IOException {
		/*File myFile = new File("F://Downloads/Room Reservation Calendar (26882 records) 2017-09-03.xlsx"); 
		FileInputStream fis = new FileInputStream(myFile); // Finds the workbook instance for XLSX file 
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); // Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = myWorkBook.getSheetAt(0); // Get iterator to all the rows in current sheet 
		Iterator<Row> rowIterator = mySheet.iterator(); // Traversing over each row of XLSX file 
		
		while (rowIterator.hasNext()) { 
		Row row = rowIterator.next(); // For each row, iterate through each columns 
		Iterator<Cell> cellIterator = row.cellIterator();
		
		while (cellIterator.hasNext()) { 
			Cell cell = cellIterator.next(); 
			switch (cell.getCellType()) { 
			case Cell.CELL_TYPE_STRING: System.out.print(cell.getStringCellValue() + "\t"); 
			break; 
			case Cell.CELL_TYPE_NUMERIC: System.out.print(cell.getNumericCellValue() + "\t"); 
			break; 
			case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t");
			break;  
			} 
			} 
		}*/


	return"redirect:/home";

	}

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("home", new Entity());
		return "home";
	}

	@GetMapping("/maps")
	public String getMappings(Model model) {
		model.addAttribute("maps", new Entity());
		return "maps";
	}

	@GetMapping("/floorname={floorname}")
	public String getFloorplan(@PathVariable String floorname, Model model) {
		model.addAttribute("floorplan", new Entity());
		return floorname;
	}
}
