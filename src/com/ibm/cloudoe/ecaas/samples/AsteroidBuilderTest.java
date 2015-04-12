package com.ibm.cloudoe.ecaas.samples;

import java.util.HashMap;

import org.junit.Test;

public class AsteroidBuilderTest {

	@Test
	public void testAsteroidBuilder() throws Exception {
		
		AsteroidBuilder builder = new AsteroidBuilder();
		HashMap asteroids = builder.buildWith(AsteroidServlet.DATA_SOURCE);
		
		System.out.println("built " + asteroids.size());
		System.out.println(asteroids.get("2012 FN"));
		System.out.println(asteroids.get("Eros"));
		System.out.println(asteroids.get("C1995 O1 Hale-Bopp"));
	}

	
	@Test
	public void testNumberFormat() throws Exception {
		Double myNum = Double.parseDouble("11.16");
	}
}
