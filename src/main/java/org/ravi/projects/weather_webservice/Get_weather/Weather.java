package org.ravi.projects.weather_webservice.Get_weather;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.ravi.projects.weather_webservice.Jsonobj.Current_Observation;
import org.ravi.projects.weather_webservice.Jsonobj.Display_location;
import org.ravi.projects.weather_webservice.Jsonobj.Image;
import org.ravi.projects.weather_webservice.uriconst.Uri_Constructor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@SuppressWarnings("unused")
@Path("/Weather")
public class Weather {
	
	private String Country, City,value;
	
	@GET 
    @Produces("text/plain")
    public String getIt() {
        return "Get  get it";
    }
	

	@POST 
    @Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")
    public String postIt(@FormParam("City") String Cty,@FormParam("Country") String Cntry,@FormParam("conditions") String Weather,@FormParam("alerts") String Alert) {
		
		this.City=Cty;
		this.Country=Cntry;
		if(Weather==null)
			value = Alert;
		else
			value = Weather;
        
        Uri_Constructor newurl = new Uri_Constructor(value,Cty,Country);
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(newurl.make_uri_call());
        JsonObject jess = element.getAsJsonObject();
        //JsonArray arr = jess.getAsJsonArray();
        //arr.get(0).get("version").getAsString();
        //String place = element.getAsString();
        JsonObject val = jess.getAsJsonObject("current_observation");
        JsonObject val2 = val.getAsJsonObject("display_location");
        Gson gson = new GsonBuilder().create();
        //Display_location disploc = gson.fromJson(val2, Display_location.class);
        //Image img = gson.fromJson(val.getAsJsonObject("image"), Image.class);
        Current_Observation currobs = gson.fromJson(val.toString(), Current_Observation.class);
        //String val3 = val2.get("full").toString()+"\n"+val2.get("state_name").toString();
        if(jess.isJsonObject())
        return currobs.getDisplay_location().getCity()+"\n"+
        		currobs.getTemp_c()+"\n"+
        		currobs.getFeelslike_c()+"\n"+
        		currobs.getWeather();  
        else
        	return "falsedude";
    }

}
