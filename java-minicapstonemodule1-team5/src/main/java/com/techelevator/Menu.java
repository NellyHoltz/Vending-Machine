package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	//variables
	private PrintWriter out;
	private Scanner in;

	//Constructor
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	private void showOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\n\rSelect an option --------->");
		out.flush();
	}

	private Object getUserWrote(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			e.getMessage();
		}
		if (choice == null) {
			out.println("\n\r" + userInput + " invalid command \n\r");
		}
		return choice;
	}


	//GET THE SELECTED OPTION
	public Object getSelectedOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			showOptions(options);
			choice = getUserWrote(options);
		}
		return choice;
	}

}
