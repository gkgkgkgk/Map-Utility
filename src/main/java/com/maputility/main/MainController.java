package com.maputility.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.maputility.dao.PeriodDao;
import com.maputility.entities.Entity;

@Controller
public class MainController {

	private static final String FILE_NAME = "F:/Downloads/Roomcal.xlsx";

	@Autowired
	StringEngine stringEngine;

	@GetMapping("/")
	public String redirect(Model model) throws IOException {
		return "redirect:/home";

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

		try {

			Sheet datatypeSheet = new XSSFWorkbook(new FileInputStream(new File(FILE_NAME))).getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Cell dateCell = cellIterator.next();
				if (!dateCell.getStringCellValue().equals("Event Date")) {
					Date date = new SimpleDateFormat("MM/dd/yy").parse(dateCell.getStringCellValue());

					if (date.getTime() > new Date().getTime()) {

						Cell infoCell = cellIterator.next();
						String cell = infoCell.getStringCellValue();
						int startTime = stringEngine.getStartTime(cell);
						// int endTime = ;
						// String description = ;

						// PeriodDao period = new PeriodDao(date, startTime, endTime, description);
						
						System.out.println(startTime + " | " + infoCell.getStringCellValue());
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return floorname;
	}
}
