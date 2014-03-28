import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.WeatherData;


public class DataFetcher {
	
	private ObjectMapper mapper;
	
	public DataFetcher(){
		mapper = new ObjectMapper();
	}
	
	public WeatherData getWeather(){
		String response = apiCall("http://opendata-download-metfcst.smhi.se/api/category/pmp1g/version/1/geopoint/lat/58.59/lon/16.18/data.json");
		
//		System.out.println(response);
		return this.jsonToPojo(response, WeatherData.class);
	}
	
	/**
	 * Deserialize the incoming json string to the giving java class  
	 * 
	 * @param input Data in json format
	 * @param inputType The class to extract the information from input too.
	 * @return Returns in the same format as in inputType
	 */
	private <T> T jsonToPojo(String input,Class<T> inputType){
		try {
			return inputType.cast(mapper.readValue(input, inputType));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Makes the url request to the url that is given as input and return the response+
	 * The return from gist api calls in only one line the method will only return the
	 * first line from the response
	 * 
	 * @param inputURL The address to make the request
	 * @return The answer or if something went wrong 
	 */
	private String apiCall(String inputURL){
		String response = "EMPTY";
		try {
			URL url = new URL(inputURL);
			URLConnection con  = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while ((response = in.readLine()) != null) {
				//System.out.println(sCurrentLine);
				sb.append(response);
			}
			response = sb.toString();		
		} catch (MalformedURLException e) {
			response = "{\"error\": \"Something went wrong with malformedurl\"}";
			e.printStackTrace();
		} catch (IOException e) {
			response = "{\"error\": \"IOExceptions happend\"}";
			e.printStackTrace();
		}
		return response; 
	}
}
