package com.ibm.cloudoe.ecaas.samples;

import java.util.Map;

import com.ibm.json.java.JSONObject;



public class Asteroid extends JSONObject{

	private MinorPlanetLine _record;
	
	public Asteroid(MinorPlanetLine record)
	{
		super();
		this.putAll(record.getMap());
		_record = record;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return _record.getName().trim();
	}

	public void setSizeKey() {
		String strDiam = (String)this.get("estDiam");
		
		//if(not)
		
		//this.put(sizeRange, arg1);
		
	}
	
}
