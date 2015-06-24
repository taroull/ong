package es.ull.taro.ong_core.services;

import java.util.ArrayList;
import java.util.HashMap;

import es.ull.taro.ong_core.domain.GeoResource;

public interface ElderlyService {
	
	public static final String BEAN_ID = "elderlyService";

	public ArrayList<GeoResource> find(String name);

	public HashMap<String, String> describeUri(String uri);
	
	public ArrayList<GeoResource> retrieveElderlyAround(String uri, int radius);
	
	public ArrayList<GeoResource> findElderlyAround(String latitude, String longitude, int radius);
}
