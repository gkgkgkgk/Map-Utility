package com.maputility.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.maputility.dao.PeriodDao;
import com.maputility.dao.RoomDao;
import com.maputility.dao.RoomDaoMin;
import com.maputility.entities.Entity;

@Controller
public class MainController {

	private static final String FILE_NAME = "F:/Downloads/Roomcal.xlsx";

	@Autowired
	StringEngine stringEngine;

	@GetMapping("/")
	public String redirect(Model model) throws IOException {
		return "login";

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

	@GetMapping("/test")
	public String getTest(Model model) {
		System.out.println("test");
		return "boi";
	}

	@GetMapping("/refresh")
	public String getRefresh(Model model) {
		System.out.println(System.currentTimeMillis());
		ArrayList<RoomDao> rooms = new ArrayList<RoomDao>();
		try {

			Sheet datatypeSheet = new XSSFWorkbook(new FileInputStream(new File(FILE_NAME))).getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Cell dateCell = cellIterator.next();

				if (!dateCell.getStringCellValue().equals("Event Date")) {
					Date date = new SimpleDateFormat("MM/dd/yy").parse(dateCell.getStringCellValue());

					if (date.getTime() > new Date().getTime()
							&& date.getTime() < new Date().getTime() + (TimeUnit.DAYS.toMillis(3))) {

						Cell infoCell = cellIterator.next();
						String cell = infoCell.getStringCellValue();
						int startTime = stringEngine.getStartTime(cell);
						int endTime = stringEngine.getEndTime(cell);
						String description = "";

						PeriodDao period = new PeriodDao(date, startTime, endTime, description);

						if (rooms.size() > 0) {
							boolean addNewRoom = true;
							for (int i = 0; i < rooms.size(); i++) {
								if (rooms.get(i).getClassName()
										.equals(stringEngine.getClassName(infoCell.getStringCellValue()))) {
									rooms.get(i).addPeriod(period);
									addNewRoom = false;
								}
							}
							if (addNewRoom) {
								rooms.add(
										new RoomDao(period, stringEngine.getClassName(infoCell.getStringCellValue())));
							}
						} else {
							rooms.add(new RoomDao(period, stringEngine.getClassName(infoCell.getStringCellValue())));
							System.out.println("adding new room");
						}

						// System.out.println(stringEngine.getClassName(infoCell.getStringCellValue()) +
						// " | " + infoCell.getStringCellValue());
					}
				}

			}
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		Gson gson = new Gson();

		try (FileWriter file = new FileWriter("src/main/webapp/Libraries/json.txt")) {
			file.write(gson.toJson(rooms));
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/home";
	}

	@RequestMapping(value = "/day={day}", method = RequestMethod.GET)
	@ResponseBody
	public String test(@PathVariable String day) {
		System.out.println(System.currentTimeMillis());
		ArrayList<RoomDao> rooms = new ArrayList<RoomDao>();

		System.out.println("recieved call for date: " + day);

		try {

			Sheet datatypeSheet = new XSSFWorkbook(new FileInputStream(new File(FILE_NAME))).getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			boolean done = false;
			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Cell dateCell = cellIterator.next();
				
				if (!dateCell.getStringCellValue().equals("Event Date") && !day.equals("favicon")) {
					Date date = new SimpleDateFormat("MM/dd/yy").parse(dateCell.getStringCellValue());
					Date currentDateObj = new SimpleDateFormat("MM-dd-yy").parse(day);

					String currentDate = new SimpleDateFormat("MM-dd-yy").format((currentDateObj));
					String newDate = dateCell.getStringCellValue().replaceAll("/", "-");
					if (newDate.equals(currentDate)) {
						System.out.println(newDate + " matches " + currentDate);
						Cell infoCell = cellIterator.next();
						String cell = infoCell.getStringCellValue();

						int startTime = stringEngine.getStartTime(cell);
						int endTime = stringEngine.getEndTime(cell);
						String description = "";

						PeriodDao period = new PeriodDao(date, startTime, endTime, description);

						if (rooms.size() > 0) {
							boolean addNewRoom = true;
							for (int i = 0; i < rooms.size(); i++) {
								if (rooms.get(i).getClassName()
										.equals(stringEngine.getClassName(infoCell.getStringCellValue()))) {
									rooms.get(i).addPeriod(period);
									addNewRoom = false;
								}
							}
							if (addNewRoom) {
								rooms.add(
										new RoomDao(period, stringEngine.getClassName(infoCell.getStringCellValue())));
							}
						} else {
							rooms.add(new RoomDao(period, stringEngine.getClassName(infoCell.getStringCellValue())));
							System.out.println("adding new room");
						}

					}

				}

			}
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		Gson gson = new Gson();

		ArrayList<RoomDaoMin> minRooms = new ArrayList<RoomDaoMin>();

		int increment = 1;

		for (RoomDao room : rooms) {

			System.out.println("Starting new room:" + room.getClassName());

			int[] array = new int[1440 / increment];
			int currentTime = 0;
			int i = 0;

			for (PeriodDao period : room.getPeriods()) {
				System.out.println("Starting new Period " + period.getStartTime() + "  " + period.getEndTime()
						+ " current Time : " + currentTime);
					System.out.println(currentTime);
					while (currentTime < period.getStartTime()) {
						array[i] = 0;
						currentTime += increment;
						i++;
					}
					System.out.println("periodEnd " + period.getEndTime());
					System.out.println("ROOM " + room.getClassName());

					while (currentTime <= period.getEndTime()) {
						System.out.println(i);
						array[i] = 1;
						currentTime += increment;
						i++;
					}
			}
			minRooms.add(new RoomDaoMin(room.getClassName(), array));
		}

		try (FileWriter file = new FileWriter("src/main/webapp/Libraries/schedule.txt")) {
			file.write(gson.toJson(minRooms));
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String arrayString = "";
		for (RoomDaoMin room : minRooms) {
			arrayString += "room" + room.getClassName();
			for (int i = 0; i < room.getTimes().length; i++) {
				arrayString += "" + room.getTimes()[i];
			}
		}

		return arrayString;
	}

}
