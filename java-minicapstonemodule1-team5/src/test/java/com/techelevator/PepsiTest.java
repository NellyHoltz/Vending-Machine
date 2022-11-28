package com.techelevator;
import org.junit.Assert;
import org.junit.Test;

public class PepsiTest {

	
	@Test
	public void pepsiTest() {

		String compare="Slurp Slurp, Yum!";
		Assert.assertEquals(compare, Pepsi.getMessage());
	}
	
}
