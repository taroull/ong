package es.ull.taro.ong_core.services;

import java.util.ArrayList;
import java.util.HashMap;

import es.ull.taro.ong_core.domain.GeoResource;

public interface PharmacyService {
	
	public static final String BEAN_ID = "pharmacyService";

	public ArrayList<GeoResource> find(String name);
	
	public HashMap<String, String> describeUri(String uri);
	
	public ArrayList<GeoResource> retrievePharmacyAround(String uri, int radius);
	
	public ArrayList<GeoResource> findPharmacyAround(String latitude, String longitude, int radius);
	
}
