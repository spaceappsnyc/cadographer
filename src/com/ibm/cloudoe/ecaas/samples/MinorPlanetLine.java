package com.ibm.cloudoe.ecaas.samples;

import java.util.HashMap;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;
import com.ibm.json.java.JSONObject;

@Record
public class MinorPlanetLine {

	public MinorPlanetLine()
	{
		super();
		theMap=new HashMap();
	}
	
	  private String name;
	  private String id;
	  private String magnitude;
	  private HashMap theMap;

	  public HashMap getMap()
	  {
		  return theMap;
	  }
	  
	  @Field(offset = 175, length = 19)
	  public String getName() {
	    return (String) theMap.get("name");
	  }
	  public void setName(String stringData) {
	    this.theMap.put("name", stringData.trim());
	  }

	  @Field(offset = 1, length = 7, align = Align.LEFT)
	  public String getId() {
	    return (String) theMap.get("id");
	  }
	  public void setId(String stringData) {
		  theMap.put("id", stringData.trim());
	  }

	  
	  @Field(offset = 9, length = 5, align = Align.RIGHT)
	  public String getMagnitude() {
	    return (String) theMap.get("magnitude");
	  }
	  public void setMagnitude(String strMagnitude) {
		  theMap.put("magnitude", strMagnitude.trim()); ;
	  }

	  private static final String MEAN_ANOMOLY_DEGREE = "meanAnomolyDegree";
	  @Field(offset = 26, length = 10, align = Align.RIGHT)
	  public String getMeanAnomalyDegree() {
	    return (String) theMap.get(MEAN_ANOMOLY_DEGREE);
	  }
	  public void setMeanAnomalyDegree(String str) {
		  theMap.put(MEAN_ANOMOLY_DEGREE, str.trim()); ;
	  }
	
	  private static final String PERIHELION = "perihelion";
	  @Field(offset = 37, length = 10, align = Align.RIGHT)
	  public String getPerihelion() {
	    return (String) theMap.get(PERIHELION);
	  }
	  public void setPerihelion(String str) {
		  theMap.put(PERIHELION, str.trim()); ;
	  }
	
	  
	  private static final String ASCENDING_NODE = "ascendingNode";
	  @Field(offset = 48, length = 10, align = Align.RIGHT)
	  public String getAscendingNode() {
	    return (String) theMap.get(ASCENDING_NODE);
	  }
	  public void setAscendingNode(String str) {
		  theMap.put(ASCENDING_NODE, str.trim()); ;
	  }
	  
	  private static final String INCLINATION = "inclination";
	  @Field(offset = 59, length = 10, align = Align.RIGHT)
	  public String getInclination() {
	    return (String) theMap.get(INCLINATION);
	  }
	  public void setInclination(String str) {
		  theMap.put(INCLINATION, str.trim()); ;
	  }
	  
	  private static final String ECCENTRICITY = "eccentricity";
	  @Field(offset = 69, length = 11, align = Align.RIGHT)
	  public String getEccentricity() {
	    return (String) theMap.get(ECCENTRICITY);
	  }
	  public void setEccentricity(String str) {
		  theMap.put(ECCENTRICITY, str.trim()); 
	  }
	  
	  private static final String MEAN_MOTION = "meanMotion";
	  @Field(offset = 80, length = 12, align = Align.RIGHT)
	  public String getMeanMotion() {
	    return (String) theMap.get(MEAN_MOTION);
	  }
	  public void setMeanMotion(String str) {
		  theMap.put(MEAN_MOTION, str.trim()); 
	  }
	  
	  private static final String SEMI_MAJOR_AXIS = "semiMajorAxis";
	  @Field(offset = 92, length = 13, align = Align.RIGHT)
	  public String getSemiMajorAxis() {
	    return (String) theMap.get(SEMI_MAJOR_AXIS);
	  }
	  public void setSemiMajorAxis(String str) {
		  theMap.put(SEMI_MAJOR_AXIS, str.trim()); 
	  }
	  
	  
}
