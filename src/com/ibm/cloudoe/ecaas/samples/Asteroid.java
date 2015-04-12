package com.ibm.cloudoe.ecaas.samples;

import java.util.HashMap;
import java.util.Map;

import com.ibm.json.java.JSONObject;



public class Asteroid extends JSONObject{

	private MinorPlanetLine _record;
	
	public Asteroid(MinorPlanetLine record)
	{
		super();
		_record = record;
		
		this.putAll(record.getMap());
		this.put(MinorPlanetLine.OBJECT_TYPE, MinorPlanetLine.ASTEROID);
	}

	public Asteroid(HashMap hashMap) {
		super();
		this.putAll(hashMap);
	}

	public Object getName() {
		
		return ((String)this.get(MinorPlanetLine.NAME)).trim();
	}
	
	public void makeComet()
	{
		this.put(MinorPlanetLine.OBJECT_TYPE, MinorPlanetLine.COMET);
	}

	public void setUpRelativeSize() {
		
		
		String strDiam = (String)this.get(MinorPlanetLine.EST_DIAM);
		
		if (strDiam != null)
		{
			Double diam = Double.valueOf(strDiam);
			String relSize = "large";
			
			if (diam < 1)
			{
				relSize = "small";
			} else if (diam < 2)
			{
				relSize = "medium";
			}
			
			this.put(MinorPlanetLine.REL_SIZE, relSize);
				
		}
		
	}

	public void setUpDistance() {
		String str = (String)this.get(MinorPlanetLine.PALERMO_SCALE_CUM);
		
		if (str != null)
		{
			
			
			this.put(MinorPlanetLine.DISTANCE, "0");
				
		}
		
	}

	
}
