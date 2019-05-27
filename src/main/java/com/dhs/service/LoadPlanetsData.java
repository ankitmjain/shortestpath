package com.dhs.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.dhs.entity.Planet;
import com.dhs.entity.Route;

@Service
public class LoadPlanetsData {

	private List<Planet> planetList = new ArrayList<>();
	private List<Route> routeList = new ArrayList<>();

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	PlanetService planetService;

	public LoadPlanetsData() {
	}

	@PostConstruct
	public void init() throws IOException {
		FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(ResourceUtils.getFile("classpath:static/planetdetails.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);

			Sheet datatypeSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= datatypeSheet.getLastRowNum(); i++) {
				Row currentRow = datatypeSheet.getRow(i);
				Planet planetObject = new Planet();
				planetObject.setPlanetNode(currentRow.getCell(0).getStringCellValue());
				planetObject.setPlanetName(currentRow.getCell(1).getStringCellValue());
				planetList.add(planetObject);
			}
			planetService.savePlanets(planetList);
			datatypeSheet = workbook.getSheetAt(1);
			Sheet datatypeSheet1 = workbook.getSheetAt(2);
			for (int i = 1; i <= datatypeSheet.getLastRowNum(); i++) {
				Row currentRow = datatypeSheet.getRow(i);
				Row currentRow1 = datatypeSheet1.getRow(i);
				Route routeObject = new Route();
				//routeObject.setRouteID(Long.valueOf(String.valueOf((int) currentRow.getCell(0).getNumericCellValue())));
				routeObject.setPlanetOrigin(currentRow.getCell(1).getStringCellValue());
				routeObject.setPlanetDestination(currentRow.getCell(2).getStringCellValue());
				routeObject.setDistance(currentRow.getCell(3).getNumericCellValue());
				if (currentRow.getCell(1).getStringCellValue().equals(currentRow1.getCell(1).getStringCellValue()) && currentRow.getCell(2).getStringCellValue().equals(currentRow1.getCell(2).getStringCellValue()))
					routeObject.setTrafficDelay(currentRow1.getCell(3).getNumericCellValue());

				routeList.add(routeObject);
			}
			System.out.println(routeList);
			planetService.saveRoutes(routeList);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
