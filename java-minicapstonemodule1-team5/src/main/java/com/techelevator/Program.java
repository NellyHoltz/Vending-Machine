package com.techelevator;

import java.io.FileNotFoundException;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		Machine vmc = new Machine(menu);
		vmc.run();
	}
}
