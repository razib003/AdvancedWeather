package com.weather.restweather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/onedayforecast")
public class OneDayForecast {

	@Path("{zip}")
	@GET
	@Produces("application/json")
	public Response oneDayForecast(@PathParam("zip") int zip) throws JSONException {
		
		String weatherAPI = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + ",us";
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(readUrl(weatherAPI));
			String result = "@Produces(\"application/json\") Output: \n\nOne Day Forecast Output: \n\n" + jsonObject;
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(204).build();
	}
	
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
