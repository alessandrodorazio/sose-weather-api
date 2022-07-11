package it.univaq.aggm;

import java.util.List;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class StartServiceWeatherApi {

	public static void main(String[] args) {
		String restAddress = "http://localhost:8080/";
		String soapAddress = "http://localhost:8093/weather";
		startRest(restAddress);
		startSoap(soapAddress);
	}
	
	public static void startRest(String restAddress) {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(CityRepository.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new CityRepository()));
        factoryBean.setAddress(restAddress);
        Server server = factoryBean.create();
        System.out.println("Server ready...");
	}

	
	public static void startSoap(String soapAddress) {
		Endpoint.publish(soapAddress, new CityRepository());
		System.out.println("SOAP server ready...");
	}
}
