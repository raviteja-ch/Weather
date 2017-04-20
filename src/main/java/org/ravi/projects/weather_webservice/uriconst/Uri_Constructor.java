package org.ravi.projects.weather_webservice.uriconst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Uri_Constructor {
	

	private String State;
	private String City;
	private String Alt_War;
	private String resp;
	
	public Uri_Constructor()
	{
		
	}
	
	public Uri_Constructor(String Value, String City, String State){
		this.State = State;
		this.City = City;
		this.Alt_War = Value;
		
	}
	
	public String make_uri_call()
	{
		return this.const_URI(Alt_War, City, State);
	}
	
	private String const_URI(String Value, String City, String State)
	{
	try {
	String url = "http://api.wunderground.com/api/24de9d170dae73a2/"+Value+"/q/"+State+"/"+City+".json";

	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", "Ravi");
	con.setRequestProperty("Accept", "application/json");
	//int responseCode = con.getResponseCode();
	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();
	resp =  response.toString();
	}catch (MalformedURLException e) {
		
        e.printStackTrace();

    } catch (IOException f) {

        f.printStackTrace();

    }
	return resp;

	}

}
