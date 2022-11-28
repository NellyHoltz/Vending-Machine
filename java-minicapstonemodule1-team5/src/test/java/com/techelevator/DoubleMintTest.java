package com.techelevator;
import org.junit.Assert;
import org.junit.Test;

public class DoubleMintTest {

	
	@Test
	public void returnsChew() {
		String compare="Chew Chew, Yum!";
		Assert.assertEquals(compare, DoubleMint.getMessage());
	}
	
}
