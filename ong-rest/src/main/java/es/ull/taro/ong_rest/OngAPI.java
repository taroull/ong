package es.ull.taro.ong_rest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ull.taro.ong_core.domain.GeoResource;
import es.ull.taro.ong_core.services.BotiquinService;
import es.ull.taro.ong_core.services.CenterService;
import es.ull.taro.ong_core.services.DBpediaService;
import es.ull.taro.ong_core.services.DisabilityService;
import es.ull.taro.ong_core.services.ElderlyService;
import es.ull.taro.ong_core.services.PharmacyService;

@Component
@Path("/")
public class OngAPI {
	
	@Autowired
	protected CenterService centerService;
	
	@Autowired
	protected PharmacyService pharmacyService;
	
	@Autowired
	protected BotiquinService botiquinService;
	
	@Autowired
	protected DisabilityService disabilityService;
	
	@Autowired
	protected ElderlyService elderlyService;
	
	@Autowired
	protected DBpediaService dbpediaService;
	
	/* Llamadas por NOMBRE */
	 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("center")
	public  ArrayList<GeoResource> findCenterByName(@QueryParam(value = "name") String name) throws Exception {
		return centerService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pharmacy")
	public ArrayList<GeoResource> findPharmacyByName(@QueryParam(value = "name") String name) throws Exception {
		return pharmacyService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("botiquin")
	public ArrayList<GeoResource> findBotiquinByName(@QueryParam(value = "name") String name) throws Exception {
		return botiquinService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("disability")
	public ArrayList<GeoResource> findDisabilityByName(@QueryParam(value = "name") String name) throws Exception {
		return disabilityService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("elderly")
	public ArrayList<GeoResource> findElderlyByName(@QueryParam(value = "name") String name) throws Exception {
		return elderlyService.find(name);
	}
	
	/* Llamadas por URI */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pharmacyuri")
	public HashMap<String, String> findPharmacyByUri(@QueryParam(value = "uri") String uri) throws Exception {
		return pharmacyService.describeUri(uri);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("botiquinuri")
	public HashMap<String, String> findBotiquinByUri(@QueryParam(value = "uri") String uri) throws Exception {
		return botiquinService.describeUri(uri);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("disabilityuri")
	public HashMap<String, String> findDisabilityByUri(@QueryParam(value = "uri") String uri) throws Exception {
		return disabilityService.describeUri(uri);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("elderlyuri")
	public HashMap<String, String> findElderlyByUri(@QueryParam(value = "uri") String uri) throws Exception {
		return elderlyService.describeUri(uri);
	}
	
	/* DBpedia*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("info")
	public HashMap<String, String> retrieveCenterInfo(@QueryParam(value = "uri") String uri) throws Exception {
		return dbpediaService.retrieveCenterInfo(uri);
	}
	
	
}
