package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface DBpediaService {
	public static final String BEAN_ID = "dBpediaService";
	
	public HashMap<String, String> retrieveCenterInfo(String uri);
}
