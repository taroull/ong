package es.ull.taro.ong_core.services;

import java.util.HashMap;

public interface CentrosService {

	public static final String BEAN_ID = "centrosService";

	public HashMap<String, String> find(String name);

}
