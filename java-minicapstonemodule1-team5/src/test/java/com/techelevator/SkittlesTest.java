package com.techelevator;
import org.junit.Assert;
import org.junit.Test;

public class SkittlesTest {

	
	@Test
	public void skittlesTest() {
		String compare="Munch Munch, Yum!";
		Assert.assertEquals(compare, Skittles.getMessage());
	}
	
}
