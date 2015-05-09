package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface CenterService {

	public static final String BEAN_ID = "centerService";

	public HashMap<String, String> find(String name);

}
