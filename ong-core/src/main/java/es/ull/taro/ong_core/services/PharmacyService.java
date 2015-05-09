package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface PharmacyService {
	
	public static final String BEAN_ID = "pharmacyService";

	public HashMap<String, String> find(String name);
}
