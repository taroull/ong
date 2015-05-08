package es.ull.taro.ong_rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ull.taro.ong_core.services.CentrosService;

@Component
@Path("/")
public class OngAPI {
	
	@Autowired
	protected CentrosService centrosService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("centros")
	public HashMap<String, String> findCentrosByName(@QueryParam(value = "name") String name) throws Exception {
		return centrosService.find(name);
	}
}
