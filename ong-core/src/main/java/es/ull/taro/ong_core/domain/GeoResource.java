package es.ull.taro.ong_core.domain;

import java.io.Serializable;

public class GeoResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uri;
	private String latitude;
	private String longitude;
	private String name;

	
	
	public GeoResource() {
	}

	public GeoResource(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
