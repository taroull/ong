package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface BotiquinService {
	
	public static final String BEAN_ID = "botiquinService";

	public HashMap<String, String> find(String name);

}
