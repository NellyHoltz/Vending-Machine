package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Machine {

	//Variables

	private static final String[] MENU1_OPTIONS = { "Display Vending Machine Items", "Purchase", "Exit" };
	private static final String[] MENU_2 = {"Feed Money", "Select Product","Finish Transaction", "Return"};
	private static final String[] MENU_3 = {"$1 Dollar", "$5 Dollars", "$10 Dollars", "Return"};
	private static List <String> MACHINE_ITEMS = new ArrayList <String>();
	private static final String[] RETURN_BUTTON = {"Return"};

	public Menu mn;

	//Constructor
	public Machine(Menu menu) {
		this.mn = menu;
	}

	public void run() throws FileNotFoundException {
		Items.readFiles();
		while (true) {
			String choice = (String) mn.getSelectedOptions(MENU1_OPTIONS);
			if (choice.equals("Display Vending Machine Items")) {
				displayVendingMachineItems();
			} else if (choice.equals("Purchase")) {
				processPurchaseMenuOptions();
			}
			else if (choice.equals("Exit")) {
				System.exit(0);
			}
		}
	}

	public void displayVendingMachineItems() throws FileNotFoundException {
		File vendItems = new File("vendingmachine.csv");
		try (Scanner vendReader = new Scanner(vendItems)) {
			while (vendReader.hasNextLine()) {
				String vendLine = vendReader.nextLine();
				Machine.MACHINE_ITEMS.add(vendLine);
			}
			for (String s : Machine.MACHINE_ITEMS) {
				System.out.println(s);
			}
		}
		String choice = (String) mn.getSelectedOptions(RETURN_BUTTON);
	}


	public void processPurchaseMenuOptions() throws FileNotFoundException {
		String purchaseMenuOption = "";
		while (!purchaseMenuOption.contentEquals("Return")) {
			purchaseMenuOption = (String) mn.getSelectedOptions(MENU_2);
			if (purchaseMenuOption.contentEquals("Feed Money")) {
				processMoneyFeed();
			}
			else if (purchaseMenuOption.equals("Select Product")) {
				processProductSelection();
			}
			else if (purchaseMenuOption.contentEquals("Finish Transaction")) {
				Cash.makeChange(Cash.getBalance());
			}
		}
	}

	public void processProductSelection() throws FileNotFoundException {
			Set<String> vendMapKeys = Items.generalMap.keySet();
			for (String key : vendMapKeys) {
				System.out.println(Items.generalMap.get(key) + "|" + Items.dataBaseStock.get(key) + " Left");
			}
			System.out.println("1) Return");
			Scanner userInput = new Scanner(System.in);
			String itemCode = userInput.nextLine();
			if (itemCode.equals("1")) {
				processPurchaseMenuOptions();
			}
			else {
			Cash.purchaseItem(itemCode);
			processProductSelection();
			}
	}

	public void processMoneyFeed() throws FileNotFoundException {
		String feedOptions = "";
		while (!feedOptions.contentEquals("Return")) {
			feedOptions = (String) mn.getSelectedOptions(MENU_3);
			System.out.println(feedOptions);
			Cash.insertMoney(feedOptions);
		}
	}

}
