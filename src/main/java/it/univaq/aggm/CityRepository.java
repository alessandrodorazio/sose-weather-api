package it.univaq.aggm;
import javax.ws.rs.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

@Path("cities")
@Produces("text/xml")
public class CityRepository {
	@GET
    @Path("getWeather/{cityName}")
    public City getCourse(@PathParam("cityName") String cityName) throws IOException, JSONException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			.url("https://weatherapi-com.p.rapidapi.com/current.json?q=" + cityName)
			.get()
			.addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
			.addHeader("X-RapidAPI-Key", "ITzvQqsKNgmshs8XqVDlBbKhtxe0p1cANiTjsn8iql5X6IiJej")
			.build();

		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
	    JSONObject Jobject = new JSONObject(jsonData);
	    JSONObject current = (JSONObject) Jobject.get("current");
		
		City c = new City();
		c.setName(cityName);
		c.setTemperature(Float.parseFloat(current.get("temp_c").toString()));
		c.setWeather(((JSONObject)current.get("condition")).get("text").toString()) ;
        return c;
    }
}
