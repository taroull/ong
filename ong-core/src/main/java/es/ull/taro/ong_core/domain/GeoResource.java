package es.ull.taro.ong_core.domain;

import java.io.Serializable;

public class GeoResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uri;
	
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
}
