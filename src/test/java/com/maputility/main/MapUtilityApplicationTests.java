package com.maputility.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.maputility.dao.PeriodDao;
import com.maputility.dao.RoomDao;
import com.maputility.dao.RoomDaoMin;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MapUtilityApplicationTests {

@Autowired	
	StringEngine stringEngine;

	
	
	@Test
	public void contextLoads() {
		String day = "09-06-17";
			System.out.println(System.currentTimeMillis());
			ArrayList<RoomDao> rooms = new ArrayList<RoomDao>();

			System.out.println("recieved call for date: " + day);

			try {

				Sheet datatypeSheet = new XSSFWorkbook(new FileInputStream(new File("F:/Downloads/Roomcal.xlsx"))).getSheetAt(0);
				Iterator<Row> rowIterator = datatypeSheet.iterator();
				boolean done = false;
				while (rowIterator.hasNext()) {
					Row currentRow = rowIterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
					Cell dateCell = cellIterator.next();

					if (!dateCell.getStringCellValue().equals("Event Date")) {
						Date date = new SimpleDateFormat("MM/dd/yy").parse(dateCell.getStringCellValue());
						String currentDate = new SimpleDateFormat("MM-dd-yy").format((new Date()));
						String newDate = dateCell.getStringCellValue().replaceAll("/", "-");
						;
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

			int increment = 1440;

			for (RoomDao room : rooms) {

				System.out.println("Starting new room:" + room.getClassName());

				int[] array = new int[1440 / increment];
				int currentTime = 360; // start at 6:00 am
				for (PeriodDao period : room.getPeriods()) {
					System.out.println("Starting new Period " + period.getStartTime() + "  " + period.getEndTime());
					int i = 0;
					while (currentTime < period.getStartTime()) {
						array[i] = 0;
						currentTime += increment;
						i++;
					}
					while (currentTime < period.getEndTime()) {
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



			System.out.println(minRooms.size());
			
			
			String arrayString = "";
				for (RoomDaoMin room : minRooms) {
					arrayString += "room" + room.getClassName();
					for(int i = 0; i < room.getTimes().length; i++) {
						arrayString +=  "" + room.getTimes()[i];
					}
				}
					
			System.out.println(arrayString);
		
	}

}
