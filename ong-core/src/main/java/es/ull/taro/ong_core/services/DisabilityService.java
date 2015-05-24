package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface DisabilityService {
	
	public static final String BEAN_ID = "disabilityService";

	public HashMap<String, String> find(String name);

	public HashMap<String, String> describeUri(String uri); 
}
