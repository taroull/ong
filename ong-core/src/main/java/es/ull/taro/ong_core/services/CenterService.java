package es.ull.taro.ong_core.services;

import java.util.ArrayList;
import java.util.HashMap;

import es.ull.taro.ong_core.domain.GeoResource;

public interface CenterService {

	public static final String BEAN_ID = "centerService";

	public  ArrayList<GeoResource> find(String name);
	
	public HashMap<String, String> describeUri(String uri);

}
