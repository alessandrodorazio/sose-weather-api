package it.univaq.aggm;
import javax.jws.WebService;
import javax.ws.rs.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

@Path("weather")
@Produces("text/xml")
@WebService(endpointInterface = "it.univaq.aggm.CityRepositoryInterface")
public class CityRepository implements CityRepositoryInterface {
	@GET @Path("byCoordinates/{coordinates}")
    public City getWeatherByCoordinates(@PathParam("coordinates") String coordinates) throws IOException, JSONException {
		JSONObject fetchedData = getData(coordinates);
		JSONObject current = (JSONObject) fetchedData.get("current");
		String cityName = fetchedData.getJSONObject("location").getString("name");
		float temperature = Float.parseFloat(current.get("temp_c").toString());
		String weatherDescription = ((JSONObject)current.get("condition")).get("text").toString() ;
        return new City(cityName, temperature, weatherDescription);
    }
	
	private JSONObject getData(String coordinates) throws IOException, JSONException {
		OkHttpClient client = new OkHttpClient();
		String url ="https://weatherapi-com.p.rapidapi.com/current.json?q=" + coordinates;
		String rapidAPIHost = "weatherapi-com.p.rapidapi.com";
		String apiKey = "ITzvQqsKNgmshs8XqVDlBbKhtxe0p1cANiTjsn8iql5X6IiJej";
		Request request = new Request.Builder().url(url).get()
				.addHeader("X-RapidAPI-Host", rapidAPIHost)
				.addHeader("X-RapidAPI-Key", apiKey).build();

		Response response = client.newCall(request).execute();
		String jsonData = response.body().string();
	    JSONObject Jobject = new JSONObject(jsonData);
	    return Jobject;
	}
}
