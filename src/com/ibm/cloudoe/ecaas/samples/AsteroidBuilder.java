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

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
			//parse the line
			MinorPlanetLine record = manager.load(MinorPlanetLine.class, inputLine);
		    
		    Asteroid theAsteroid = new Asteroid(record);
		    
		    asteroids.put(theAsteroid.getName(), theAsteroid);
		}
		
	    in.close();

	    asteroids = enrichWithThreatInfo(asteroids);
	    
		return asteroids;
	}

	
	private HashMap enrichWithThreatInfo(HashMap asteroids) throws Exception{
		// load the enrichment data
		//FileUtils.writeStringToFile(new File("YAGOTH.txt"), "SCREAMM!");
		BufferedReader in = new BufferedReader(new FileReader(new File("WebContent/impactdatalite.csv")));
		String inputLine = null;
		
		while ((inputLine = in.readLine()) != null) {
			//parse the line
			System.out.println(inputLine);
			String[] data= inputLine.split(",");
			
			//shove it into a map
			
		    
		}
		
		// Enrich the asteroids
		Iterator iterator = asteroids.entrySet().iterator();
		while(iterator.hasNext()){
		   Map.Entry entry = (Entry) iterator.next();
		   //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
		   // pick the corresponding entry from the enrichment data
		   
		   // add it to the asteroid's map
		}
		
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
		return(response.toString());

	}

	// HTTP POST request
	protected void sendPost() throws Exception {
//
//		String url = "https://selfsolve.apple.com/wcResults.do";
//		URL obj = new URL(url);
//		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//		// add reuqest header
//		con.setRequestMethod("POST");
//		con.setRequestProperty("User-Agent", USER_AGENT);
//		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//
//		// Send post request
//		con.setDoOutput(true);
//		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//		wr.writeBytes(urlParameters);
//		wr.flush();
//		wr.close();
//
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + urlParameters);
//		System.out.println("Response Code : " + responseCode);
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(
//				con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//
//		// print result
//		System.out.println(response.toString());
//
	}


}
