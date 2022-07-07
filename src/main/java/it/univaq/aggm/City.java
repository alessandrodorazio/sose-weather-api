package it.univaq.aggm;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "City")
public class City {
	private String name;
	private float temperature;
	private String weather;
	
	public City() {
		
	}
	
	public City(String name, float temperature, String weather) {
		this.name = name;
		this.temperature = temperature;
		this.weather = weather;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTemperature(float temp) {
		this.temperature = temp;
	}
	
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getTemperature() {
		return this.temperature;
	}
	
	public String getWeather() {
		return this.weather;
	}
}
