package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Items {
	
	private static File itemsList = new File("vendingmachine.csv");
	public static Map<String, String> generalMap = new HashMap<>();
	public static Map<String, BigDecimal> priceList = new HashMap<>();
	public static Map<String, String> nameList = new HashMap<>();
	public static Map<String, Integer> dataBaseStock = new HashMap<>();
	public static Map<String, String> vendMapType = new HashMap<>();
	public static String[] itemsArray = new String[4];
	
	public static void readFiles() throws FileNotFoundException {
		try (Scanner vendReader = new Scanner(itemsList)) {
			while (vendReader.hasNextLine()) {
				String vendLine = vendReader.nextLine();
				itemsArray = vendLine.split(",");
				generalMap.put(itemsArray[0], vendLine);
				priceList.put(itemsArray[0], new BigDecimal(itemsArray[2]));
				nameList.put(itemsArray[0], itemsArray[1]);
				dataBaseStock.put(itemsArray[0], 5);
				vendMapType.put(itemsArray[0], itemsArray[3]);

			}

		}
	}
}
