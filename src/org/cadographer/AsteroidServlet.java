package org.cadographer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.json.java.JSONObject;



@WebServlet(urlPatterns ="/asteroid", loadOnStartup=1)
public class AsteroidServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static HashMap _asteroids;
	private static Log logger = LogFactory.getLog(AsteroidServlet.class);
	
	public static final String DATA_SOURCE = "http://www.minorplanetcenter.net/iau/MPCORB/NEA.txt";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AsteroidServlet() {
		super();
		//ECacheConnection.initECaaS();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	/**
	 * Process /ecaas reuqest and return the relevant processing results
	 *  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//set request and response configuration
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		try {
			//get request data
			String key = request.getParameter("key");
			//Object retrievedValue;
			//String mapName = "sample.NONE.P";
			//Process operation value and return processing results 
			
			makeAsteroids();
			
			
			JSONObject json = null;
			
			if (key!=null){
				 json = (JSONObject)_asteroids.get(key);
			} else
			{
				 json = shipAsteroids();
			}

			//json.putAll(whatsMyRoot());

			response.getWriter().write(json.serialize());
			
	
		} catch (Exception e) {
			System.out.println("Failed to perform operation on map.");
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	private Map whatsMyRoot() {
		HashMap myMap = new HashMap();
		
		File root = new File(".");
		myMap.put("root", root.getAbsolutePath());
		if (root.isDirectory()){
			String[] listing = root.list();
			StringBuilder contents = new StringBuilder();
			
			for (int i=0;i<listing.length;i++)
			{
				contents.append(listing[i] + "|");
			}
			
			myMap.put("root contents", contents.toString());	
		}
		
		return myMap;
	}

	private JSONObject shipAsteroids() {
		// send all asteroids as JSON
	
		JSONObject jsonAs = new JSONObject();
		
		jsonAs.putAll(_asteroids);
		
		return jsonAs;
	}

	private void makeAsteroids() throws Exception{
		// TODO Auto-generated method stub
		if (_asteroids == null){
			AsteroidBuilder builder = new AsteroidBuilder();
			_asteroids = builder.buildWith(DATA_SOURCE);
			
		}
	}
	
	
}
