package com.ibm.cloudoe.ecaas.samples;

import java.util.HashMap;

import org.junit.Test;

public class AsteroidBuilderTest {

	@Test
	public void testAsteroidBuilder() throws Exception {
		
		AsteroidBuilder builder = new AsteroidBuilder();
		HashMap asteroids = builder.buildWith(AsteroidServlet.DATA_SOURCE);
		
		System.out.println(asteroids.get("2012 FN"));
	}

	
	@Test
	public void testNumberFormat() throws Exception {
		Double myNum = Double.parseDouble("11.16");
	}
}
