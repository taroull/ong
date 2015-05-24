package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface ElderlyService {
	
	public static final String BEAN_ID = "elderlyService";

	public HashMap<String, String> find(String name);

	public HashMap<String, String> describeUri(String uri); 
}
