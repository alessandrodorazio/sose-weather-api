package it.univaq.aggm;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.codehaus.jettison.json.JSONException;

@WebService
public interface CityRepositoryInterface {
	@WebMethod
    public City getWeatherByCoordinates(String coordinates) throws IOException, JSONException;
}
