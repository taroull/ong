package es.ull.taro.ong_core.domain;

public class CenterResource extends GeoResource {

	private static final long serialVersionUID = 1L;
	
	private String category;
	
	public CenterResource() {
	}
	
	public CenterResource(String uri) {
		super(uri);
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
