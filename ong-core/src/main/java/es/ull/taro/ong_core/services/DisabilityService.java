package es.ull.taro.ong_core.services;

import java.util.ArrayList;
import java.util.HashMap;

import es.ull.taro.ong_core.domain.GeoResource;

public interface DisabilityService {
	
	public static final String BEAN_ID = "disabilityService";

	public ArrayList<GeoResource> find(String name);

	public HashMap<String, String> describeUri(String uri);
	
	public ArrayList<GeoResource> retrieveDisabilityAround(String uri, int radius);
	
	public ArrayList<GeoResource> findDisabilityAround(String latitude, String longitude, int radius);
}
