package org.cadographer;

import java.util.HashMap;

public class ConfigurationServices {
	
	public static HashMap mapping = buildMap();
	

	public static final String getEnv()
	{
		String env = System.getenv("env_type");
		if (env == null)
		{
			return "dev";
		}
		
		return env;
	}
	
	private static HashMap buildMap() {
		HashMap theMap = new HashMap();
		
		addKey(theMap, "dev", "fileRoot", "WebContent/");
		addKey(theMap, "production", "fileRoot", "apps/myapp.war/");
		
		return theMap;
	}

	private static void addKey(HashMap theMap, String env, String key, String val) {
		
		HashMap envMap = (HashMap) theMap.get(env);
		if (envMap == null)
		{
			envMap = new HashMap();
		}
		envMap.put(key, val);
		theMap.put(env, envMap);
	}

	public static final String get(String key)
	{
		String env = getEnv();
		
		System.out.println(env);
		System.out.println(mapping);
		
		return (String)((HashMap)mapping.get(env)).get(key);
	}
}
