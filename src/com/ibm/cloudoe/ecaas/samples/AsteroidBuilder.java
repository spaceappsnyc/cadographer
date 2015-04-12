package com.ibm.cloudoe.ecaas.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;

public class AsteroidBuilder {

	private final String USER_AGENT = "Mozilla/5.0";
	private static FixedFormatManager manager = new FixedFormatManagerImpl();

	// HTTP GET request
	public HashMap buildWith(String url) throws Exception {
		HashMap asteroids = new HashMap();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			// parse the line
			MinorPlanetLine record = manager.load(MinorPlanetLine.class,
					inputLine);

			Asteroid theAsteroid = new Asteroid(record);

			asteroids.put(theAsteroid.getName(), theAsteroid);
		}

		in.close();

		asteroids = enrichWithThreatInfo(asteroids);

		return asteroids;
	}

	private HashMap enrichWithThreatInfo(HashMap asteroids) throws Exception {
		// load the enrichment data
		// FileUtils.writeStringToFile(new File("YAGOTH.txt"), "SCREAMM!");
		BufferedReader in = new BufferedReader(new FileReader(new File(
				"WebContent/impactdatalite.csv")));
		String inputLine = null;
		
		HashMap threatDataMap = new HashMap();

		while ((inputLine = in.readLine()) != null) {
			// parse the line
			// System.out.println(inputLine);
			String[] data = inputLine.split(",");
			
			HashMap values = new HashMap();
			// shove data into a map
			values.put("name", data[0]);
			values.put("yearRange", data[1]);
			values.put("potentialImpacts", data[2]);
			values.put("impactProb", data[3]);
			values.put("vKmPerSec", data[4]);
			values.put(MinorPlanetLine.EST_DIAM, data[6]);
			values.put(MinorPlanetLine.PALERMO_SCALE_CUM, data[7]);
			values.put("palermoScaleMax", data[8]);
			values.put("torinoScale", data[9]);

			// shove that map into a map
			threatDataMap.put(data[0], values);

		}

		// Enrich the asteroids
		Iterator iterator = asteroids.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			// System.out.printf("Key : %s and Value: %s %n", entry.getKey(),
			// entry.getValue());
			// pick the corresponding entry from the enrichment data

			Asteroid asteroid = (Asteroid)entry.getValue();
			
			
			HashMap extraValues = (HashMap) threatDataMap.get(entry.getKey());
			

			// add it to the asteroid's map
			if (extraValues != null)
			{
				asteroid.putAll(extraValues);	
			}
			asteroid.setUpRelativeSize();
			asteroid.setUpDistance();
		}
		
		// Enrich a couple special ones
		Asteroid eros = (Asteroid) asteroids.get("Eros");
		eros.put(MinorPlanetLine.EST_DIAM, "11.2");
		eros.put(MinorPlanetLine.EST_VALUE, "2400000000");
		eros.put(MinorPlanetLine.COMPOSITION, "aluminum, gold, platinum");
		eros.setUpRelativeSize();
		
		
		Asteroid _1986_DA = (Asteroid) asteroids.get("1986 DA");
		_1986_DA.put(MinorPlanetLine.EST_DIAM, "2.2");
		_1986_DA.put(MinorPlanetLine.EST_VALUE, "5000000");
		_1986_DA.put(MinorPlanetLine.COMPOSITION, "gold, platinum, iron");
		_1986_DA.setUpRelativeSize();
		
		// And then... add a comet!
		HashMap halebopp = new HashMap();
		halebopp.put(MinorPlanetLine.NAME, "C1995 O1 Hale-Bopp");
		halebopp.put(MinorPlanetLine.ECCENTRICITY, "0.995038361");
		halebopp.put(MinorPlanetLine.ASCENDING_NODE, "282.4709323");
		halebopp.put(MinorPlanetLine.PERIHELION, "130.5943673");
		halebopp.put(MinorPlanetLine.INCLINATION, "89.4283468");
		halebopp.put(MinorPlanetLine.SEMI_MAJOR_AXIS, "186");
		halebopp.put(MinorPlanetLine.OBJECT_TYPE, MinorPlanetLine.COMET);
		halebopp.put(MinorPlanetLine.EST_DIAM, "40");
		Asteroid comet = new Asteroid(halebopp);
		comet.setUpRelativeSize();
		comet.makeComet();
		asteroids.put(comet.getName(), comet);
		return asteroids;
	}

	protected String sendGet(String theURL) throws Exception {

		String url = theURL;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {

			response.append(inputLine);
		}
		in.close();

		// print result
		return (response.toString());

	}

	// HTTP POST request
	protected void sendPost() throws Exception {
		//
		// String url = "https://selfsolve.apple.com/wcResults.do";
		// URL obj = new URL(url);
		// HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		//
		// // add reuqest header
		// con.setRequestMethod("POST");
		// con.setRequestProperty("User-Agent", USER_AGENT);
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		//
		// String urlParameters =
		// "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		//
		// // Send post request
		// con.setDoOutput(true);
		// DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		// wr.writeBytes(urlParameters);
		// wr.flush();
		// wr.close();
		//
		// int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);
		//
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// con.getInputStream()));
		// String inputLine;
		// StringBuffer response = new StringBuffer();
		//
		// while ((inputLine = in.readLine()) != null) {
		// response.append(inputLine);
		// }
		// in.close();
		//
		// // print result
		// System.out.println(response.toString());
		//
	}

}
