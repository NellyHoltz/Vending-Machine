package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cash {

	//Variables
	public static BigDecimal balance = new BigDecimal(0.00).setScale(2);
	private static int quarters, dimes,nickels;
	private static double balanceD;
	private static BigDecimal pastBalance = balance;
	

	//GetSet
	public static BigDecimal getBalance() {
		return Cash.balance;
	}



	public static void purchaseItem (String itemCode) throws FileNotFoundException {
		if (!Items.generalMap.containsKey(itemCode.trim())) {
			System.out.println("CODE INVALID");
		}

		if (Items.generalMap.containsKey(itemCode)) {
			if (balance.compareTo(Items.priceList.get(itemCode))==-1) {
				System.out.println("INSUFFICIENT FUNDS");
			}
			else if (Items.dataBaseStock.get(itemCode)==0) {
				System.out.println("OUT OF STOCK");
			}
			else if (balance.compareTo(Items.priceList.get(itemCode))==1 || (balance.compareTo(Items.priceList.get(itemCode))==0)) {
				BigDecimal wasBalance = balance;
				balance = balance.subtract(Items.priceList.get(itemCode));
				Items.dataBaseStock.put(itemCode, Items.dataBaseStock.get(itemCode)-1);
				writeLog(Items.nameList.get(itemCode), wasBalance, balance);
				System.out.println("THANK YOU, FOR YOUR PURCHASE! ITEM: "+Items.nameList.get(itemCode)+"! \n"+"BALANCE: "+balance);
				if (itemCode.contains("A")) {
					Lays.getMessage();
				}
				else if (itemCode.contains("B")) {
					Skittles.getMessage();
				}
				else if (itemCode.contains("C")) {
					Pepsi.getMessage();
				}
				else {
					DoubleMint.getMessage();
				}
			}
		}
	}
	
	

	
	public static void makeChange(BigDecimal balance) throws FileNotFoundException {
		BigDecimal balanceWas = balance;
		balanceD = (balance.doubleValue())*100;
		quarters = (int) (balanceD/25);
		balanceD = balanceD - (quarters*25);
		dimes = (int) (balanceD/10);
		balanceD = balanceD - (dimes*10);
		nickels = (int) (balanceD/5);
		Cash.balance = balance.multiply(new BigDecimal(0.00));
		writeLog("GIVE CHANGE", balanceWas, balance);
		balanceWas = balanceWas.multiply(new BigDecimal(0.00));
		System.out.println("CHANGE: "+ quarters + " quarters " + dimes + " dimes " + nickels + " nickels.");
	}
	
	
	public static void writeLog (String transaction, BigDecimal was, BigDecimal currentBalance) throws FileNotFoundException {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		String formattedDate = dateTime.format(myFormatObj);
		
		
		try(PrintWriter log = new PrintWriter(new FileOutputStream(new File("SalesLog.txt"), true))) {
			log.println(formattedDate + " " + transaction + " " + was + " " + currentBalance);
		}
	}

	public static void insertMoney (String feed) throws FileNotFoundException {

		if (feed.equals("$1 Dollar")) {
			balance = balance.add(new BigDecimal (1.00));
		}
		else if (feed.equals("$5 Dollars")) {
			balance = balance.add(new BigDecimal (5.00));
		}
		else if (feed.equals("$10 Dollars")) {
			balance = balance.add(new BigDecimal (10.00));
		}
		writeLog("FEED MONEY", pastBalance, balance);
		System.out.println("BALANCE: " + balance);
	}
	


	
	
	
}
		