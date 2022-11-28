package com.techelevator;
import org.junit.Assert;
import org.junit.Test;

public class LaysTest {

	
	@Test
	public void laysTest() {
		String compare="Crunch Crunch, Yum!";
		Assert.assertEquals(compare, Lays.getMessage());
	}
	
}
