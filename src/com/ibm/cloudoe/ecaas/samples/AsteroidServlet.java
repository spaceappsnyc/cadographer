package com.ibm.cloudoe.ecaas.samples;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONArtifact;
import com.ibm.json.java.JSONObject;

@WebServlet(urlPatterns ="/asteroid", loadOnStartup=1)
public class AsteroidServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static HashMap _asteroids;
	
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
			
			JSONArtifact json = shipAsteroids();
			
			response.getWriter().write(json.serialize());
			
			//
			
//			if ("get".equals(operation)) {
//				// get value of this key.
//				retrievedValue = ECacheConnection.getData(mapName, key);
//				response.getWriter().write(retrievedValue == null ? "null" : retrievedValue.toString());
//				System.out.println("retrieved: " + retrievedValue);
//			} else if ("put".equals(operation)) {
//				// update or insert this value.
//				ECacheConnection.postData(mapName, key, newValue);
//				response.getWriter().write("Put successfull.");
//				System.out.println("put key=" + key + " value=" + newValue);
//			} else if ("delete".equals(operation)) {
//				// delete this key/value.
//				ECacheConnection.deleteData(mapName, key);
//				response.getWriter().write("Remove successfull.");
//				System.out.println("deleted key=" + key);
//			} else if ("all".equals(operation)) {
//				// get all key/value
//				List<ECache> list = ECacheConnection.getAllData(mapName);
//				String res = list.toString();
//				response.getWriter().write(res);
//				System.out.println("grid entries:" + res);
//				System.out.println("grid entries size:" + list == null ? 0: list.size());
//			}
		} catch (Exception e) {
			System.out.println("Failed to perform operation on map.");
			e.printStackTrace();
			response.setStatus(500);
		}
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
